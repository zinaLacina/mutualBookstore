package com.bookstore.mutual.repositories;



import com.bookstore.mutual.domain.UserBookstore;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends MongoRepository<UserBookstore, String> {
    Optional<List<UserBookstore>> findByFullName(String firstName);
    Optional<UserBookstore> findByUsernameEqualsIgnoreCase(String username);
    Optional<UserBookstore> findById(String id);
}
