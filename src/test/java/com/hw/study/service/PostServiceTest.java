package com.hw.study.service;

import com.hw.study.dto.post.PostResponse;
import com.hw.study.entity.Post;
import com.hw.study.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void 게시글_조회_테스트() throws Exception {
        // given
        User user = createUser("글작성자");
        Post post = createPost("글제목1", "글내용1", user);

        entityManager.persist(user);
        entityManager.persist(post);

        Long id = post.getId();

        entityManager.flush();
        entityManager.clear();

        // when
        PostResponse findPostResponse = postService.readById(id);

        // then
        Assertions.assertThat(findPostResponse.getId()).isEqualTo(id);
        Assertions.assertThat(findPostResponse.getSubject()).isEqualTo("글제목1");
        Assertions.assertThat(findPostResponse.getContent()).isEqualTo("글내용1");
        Assertions.assertThat(findPostResponse.getWriterNickname()).isEqualTo("글작성자");
    }

    private static Post createPost(String subject, String content, User user) {
        return Post.builder()
                .subject(subject)
                .content(content)
                .user(user)
                .build();
    }

    private static User createUser(String nickname) {
        User user = User.builder()
                .nickname(nickname)
                .build();
        return user;
    }

}