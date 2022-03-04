package com.darkduck.Staff.Repositories;

import com.darkduck.Staff.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    public Post findByName(String name);
}
