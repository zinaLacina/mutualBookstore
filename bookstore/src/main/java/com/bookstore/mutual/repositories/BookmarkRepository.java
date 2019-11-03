package com.bookstore.mutual.repositories;

import java.util.List;
import java.util.Optional;

import com.bookstore.mutual.domain.Bookmark;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BookmarkRepository extends MongoRepository<Bookmark, String> {
    Optional<List<Bookmark>> findByAuthorContainsIgnoreCase(String authorName);
    Optional<List<Bookmark>> findByUserId(String userId);
    Optional<List<Bookmark>> findAllByUserId(String userId);
    Optional<List<Bookmark>> findByNameContainsIgnoreCase(String name);
    Optional<Bookmark> findByUserIdAndId(String userId, String id);
    @Override
    Iterable<Bookmark> findAllById(Iterable<String> strings);

    void deleteBookByUserId(String id);


}

