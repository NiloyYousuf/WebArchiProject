package com.example.classroom.login;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class CourseRegDTO {
    private Long class_id;
    private Long stud_id;
}
