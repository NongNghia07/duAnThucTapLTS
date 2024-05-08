package com.example.duan.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseDTO {
    private int id;
    @NotBlank(message = "Name can't be null")
    private String name;
    @NotBlank(message = "introduce can't be null")
    private String introduce; // giới thiệu
    private String imageCourse;
    private String code; // random code
    @NotBlank(message = "price can't be null")
    private double price;
    @NotBlank(message = "totalCourseDuration can't be null")
    private int totalCourseDuration; // tổng thời lượng khóa học
    private int numberOfStudent; // số học viên
    private int numberOfPurchases; // số lượng mua hàng
    private UserDTO creator;
    private Set<BillDTO> bills;
    private Set<CourseSubjectDTO> courseSubjects;
}
