package com.hw.study;

import com.hw.study.entity.Post;
import com.hw.study.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.xml.bind.SchemaOutputResolver;

@Slf4j
@Component
@RequiredArgsConstructor
public class Init {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1(); //테스트 데이터 추가
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            User user = createUser("글 작성자 닉네임");
            Post post = createPost("글제목입니다", "글내용입니다.", user);

            em.persist(post);

            em.flush();
            em.clear();

//            Post findPost = em.find(Post.class, 1L); //N+1 발생!

            Post findPost = em.createQuery("select p from Post p join fetch p.user", Post.class)
                    .getSingleResult(); //Fetch Join 사용하여 문제 해결

            System.out.println(findPost.getUser().getClass());
            System.out.println(findPost.getUser().getNickname());
        }

        private Post createPost(String subject, String content, User user) {
            return Post.builder()
                    .subject(subject)
                    .content(content)
                    .user(user)
                    .build();
        }

        private User createUser(String nickname) {
            User user = User.builder()
                    .nickname(nickname)
                    .build();
            return user;
        }
    }
}
