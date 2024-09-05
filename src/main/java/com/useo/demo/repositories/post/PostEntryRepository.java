package com.useo.demo.repositories.post;

import com.useo.demo.entities.post.PostEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

    @Repository
    public interface PostEntryRepository extends CrudRepository<PostEntry, Long> {
        Optional<PostEntry> findByTitle(String title);
    }