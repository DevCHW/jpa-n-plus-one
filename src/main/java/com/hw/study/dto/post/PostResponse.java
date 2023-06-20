package com.hw.study.dto.post;

import com.hw.study.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Long id; //게시글번호
    private String subject; //제목
    private String content; //내용
    private String writerNickname; //작성자 닉네임

    public PostResponse toDto(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .subject(post.getSubject())
                .content(post.getContent())
                .writerNickname(post.getUser().getNickname())
                .build();
    }
}
