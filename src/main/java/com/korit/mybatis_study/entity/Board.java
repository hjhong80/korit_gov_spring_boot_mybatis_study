package com.korit.mybatis_study.entity;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {
    private Integer boardId;
    private String title;
    private String content;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;
}
