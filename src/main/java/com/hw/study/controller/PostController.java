package com.hw.study.controller;

import com.hw.study.dto.post.PostResponse;
import com.hw.study.entity.Post;
import com.hw.study.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> post(@PathVariable Long id) {
        PostResponse response = postService.readById(id);
        return ResponseEntity.ok(response);
    }
}
