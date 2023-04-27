package com.example.blogmake.posts.controller;

import com.example.blogmake.posts.entity.Post;
import com.example.blogmake.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/list")
    @ResponseBody
    public List<Post> getPostList() {
        List<Post> postList = postService.findAll();
        return postList;
    }
    @GetMapping("/write")
    public String getPostWriteView() {
        return "/posts/post_write";
    }

    @PostMapping("/write")
    public String postWrite(
            String title, String body
    ) {
        Long savedId = postService.save(title, body);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String getPost(
            @PathVariable Long id,
            Model model
    ) {
        Post findPost = postService.getById(id);

        model.addAttribute("post", findPost); // Map<String, Object>
        return "/post_detail";
    }



}

