package com.example.duan.DTO;

import com.example.duan.Entity.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoHomeWorkDTO {
    private int id;
    private String homeWorkStatus;
    private boolean isFinished;
    private String actualOutput;
    private String doneTime;
    private int userId;
    private int practiceId;
    private int registerStudyId;
    private int runTestCases;
    private Grade grade;
}
