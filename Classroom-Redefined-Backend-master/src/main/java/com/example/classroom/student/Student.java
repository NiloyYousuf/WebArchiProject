package com.example.classroom.student;


import com.example.classroom.classroom.Classroom;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
public class Student  {


    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private  Long id;



    private Long studid;
    private int semester;

    private  String email;

    private String password;
    private String name;

    private  String dept;

    private float cgpa;

    private int session;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "students")
    private Set<Classroom> classrooms = new HashSet<>();




}
