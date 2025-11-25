package com.korit.mybatis_study.dto;

import com.korit.mybatis_study.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoEditReqDto {
    private Integer todoId;
    private String title;
    private String content;

    public Todo toEntity() {
        return Todo.builder()
                .todoId(todoId)
                .title(title)
                .content(content)
                .build();
    }
}
