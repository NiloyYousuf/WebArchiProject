package com.example.classroom.teacher;


import com.example.classroom.classroom.Classroom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Long teachid;
    private String password;

    private String designation;
    private String dept;
    private String contact_mail;

    @OneToMany(mappedBy = "teacher")
    private Set<Classroom> classrooms;


}
