package com.example.duan.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PracticeDTO {
    private int id;
    private String practiceCode;
    private String level;
    private String title;
    private String topic;
    private String expect_output;
    private boolean is_required;
    private boolean is_deleted;
    private double medium_score;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private int programingLanguageId;
    private int subjectDetailId;
}
