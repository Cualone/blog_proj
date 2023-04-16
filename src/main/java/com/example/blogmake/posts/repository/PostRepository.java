package com.example.blogmake.posts.repository;

import com.example.blogmake.posts.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByTitleContaining(String title, Pageable pageable);


}
