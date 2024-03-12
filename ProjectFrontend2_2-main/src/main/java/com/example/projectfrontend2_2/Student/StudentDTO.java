package com.example.projectfrontend2_2.Student;

import java.util.ArrayList;

public class StudentDTO  {

    private  Long id;



    private Long studid;
    private int semester;


    private String name;

    private  String dept;

    private  String email;

    private float cgpa;

    private int session;
    private ArrayList<Long> classroom_id = new ArrayList<>();
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudid() {
        return studid;
    }

    public void setStudid(Long studid) {
        this.studid = studid;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    public ArrayList<Long> getClassroom_id() {
        return classroom_id;
    }

    public void setClassroom_id(ArrayList<Long> classroom_id) {
        this.classroom_id = classroom_id;
    }

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }
}
