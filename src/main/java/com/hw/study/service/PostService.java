package com.hw.study.service;

import com.hw.study.dto.post.PostResponse;
import com.hw.study.entity.Post;
import com.hw.study.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    public PostResponse readById(Long id) {
//        Post post = postRepository.findById(id).orElseThrow(RuntimeException::new); //N+1 발생!
        Post post = postRepository.findWithUserById(id).orElseThrow(RuntimeException::new); //EntityGraph를 사용한 메소드
        return new PostResponse().toDto(post);
    }
}
