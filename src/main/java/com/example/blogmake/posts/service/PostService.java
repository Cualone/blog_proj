package com.example.blogmake.posts.service;

import com.example.blogmake.posts.entity.Post;
import com.example.blogmake.posts.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // Post와 관련된 핵심 기능
    // DB
    // TRANSACTION : sql 쿼리를 작업 단위로 묶어서?


    // 포스트 검색
    public List<Post> findAllByTitle(String title, Pageable pageable) {

        List<Post> postList = postRepository.findAllByTitleContaining(title, pageable);
        return postList;

    }

    // 포스트 목록
    public List<Post> findAll() {
        List<Post> postList = postRepository.findAll();
        return postList;
    }

    // 포스트 삭제
    @Transactional
    public void removeById(Long id) {

        // 1. 포스트 객체를 불러온다
        Post findPost = getById(id);

        if(findPost == null) {
            return;
        }

        // 2. 삭제
        postRepository.delete(findPost);


    }


    // 포스트 수정
    @Transactional
    public void updateById(Long id, String title, String body) {

        Post findPost = getById(id);

        if(findPost == null) {
            return;
        }

        findPost.setTitle(title);
        findPost.setBody(body);

    }

    @Transactional
    public void save (String title, String body, LocalDateTime createdAt, LocalDateTime updatedAt) {
        postRepository.save(Post.createPost(title, body, createdAt, updatedAt)).getId();
    }

    // 포스트 검색
    public Post getById(Long id) {
        Optional<Post> findPost = postRepository.findById(id);

        if(findPost.isPresent()) {
            return findPost.get();
        }
        return null;
    }


}
