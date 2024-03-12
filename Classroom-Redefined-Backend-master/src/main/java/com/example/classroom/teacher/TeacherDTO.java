package com.example.classroom.teacher;


import com.example.classroom.classroom.Classroom;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
public class TeacherDTO {
    private Long id;

    private String name;

    private Long teachid;
    private String password;

    private String designation;
    private String dept;
    private String contact_mail;

    private Set<Long> classrooms_id = new HashSet<>();

    public TeacherDTO(Teacher t) {
        this.id = t.getId();
        this.name = t.getName();
        this.teachid = t.getTeachid();
        this.password = t.getPassword();
        this.designation = t.getDesignation();
        this.dept = t.getDept();
        this.contact_mail = t.getContact_mail();
        for(Classroom x : t.getClassrooms()){
            classrooms_id.add(x.getId());
        }
    }
}
