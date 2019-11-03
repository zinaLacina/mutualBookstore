package com.bookstore.mutual.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.bookstore.mutual.domain.Bookmark;
import com.bookstore.mutual.domain.UserBookstore;
import com.bookstore.mutual.exceptions.UsernameAlreadyExistsException;
import com.bookstore.mutual.repositories.BookmarkRepository;
import com.bookstore.mutual.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    UserService(UserRepository userRepository,
                BookmarkRepository bookmarkRepository) {
        this.userRepository = userRepository;
        this.bookmarkRepository = bookmarkRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UserBookstore> user = userRepository.findByUsernameEqualsIgnoreCase(s);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("Unable to find the user with email " + s);
        }

        return new User(user.get().getUsername(),
                user.get().getPassword(), Collections.emptyList());
    }

    UserBookstore getUserById(String id) {
        Optional<UserBookstore> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() ->
                new RuntimeException("Unable to find user by id " + id));
    }

    List<UserBookstore> getUsersByFirstName(String firstName) {
        Optional<List<UserBookstore>> userList = userRepository.findByFullName(firstName);

        return userList.orElse(new ArrayList<>());
    }



    UserBookstore getUserByUsername(String userName) {
        Optional<UserBookstore> userList = userRepository.findByUsernameEqualsIgnoreCase(userName);

        return userList.orElse(new UserBookstore());
    }

    List<UserBookstore> getAllUsers() {
        return userRepository.findAll();
    }

    void deleteAllUsers() {
        userRepository.deleteAll();
    }

    UserBookstore createUser(UserBookstore bookstoreUser) {
        userRepository.save(bookstoreUser);
        return bookstoreUser;
    }

    UserBookstore updateUser(UserBookstore bookstoreUser) {
        Optional<UserBookstore> originalUser = userRepository.findById(bookstoreUser.getId());

        if (!originalUser.isPresent())
            throw new UsernameNotFoundException("Unable to find the bookstoreUser : " + bookstoreUser.getUsername());

        UserBookstore modifiablebookstoreUser = originalUser.get();

        modifiablebookstoreUser.setFullName(bookstoreUser.getFullName());
        modifiablebookstoreUser.setUsername(bookstoreUser.getUsername());
        modifiablebookstoreUser.setPassword(bookstoreUser.getPassword());
        modifiablebookstoreUser.setPhone(bookstoreUser.getPhone());

        userRepository.save(modifiablebookstoreUser);

        return modifiablebookstoreUser;
    }

    void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

    List<Bookmark> getOwnedBooks(String userId) {
        Optional<List<Bookmark>> optionalBooks =
                bookmarkRepository.findAllByUserId(userId);

        return optionalBooks.orElse(new ArrayList<>());
    }

    public UserBookstore saveUser (UserBookstore newUser){

        try{
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            //Username has to be unique (exception)
            newUser.setUsername(newUser.getUsername());
            // Make sure that password and confirmPassword match
            // We don't persist or show the confirmPassword
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);

        }catch (Exception e){
            throw new UsernameAlreadyExistsException("Username '"+newUser.getUsername()+"' already exists");
        }

    }
}