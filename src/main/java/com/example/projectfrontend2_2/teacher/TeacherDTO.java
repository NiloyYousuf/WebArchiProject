package com.example.projectfrontend2_2.teacher;

import java.util.Set;

public class TeacherDTO {

    private Long id;

    private String name;

    private Long teachid;
    private String password;

    private String designation;
    private String dept;
    private String contact_mail;

    private Set<Long> classrooms_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTeachid() {
        return teachid;
    }

    public void setTeachid(Long teachid) {
        this.teachid = teachid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getContact_mail() {
        return contact_mail;
    }

    public void setContact_mail(String contact_mail) {
        this.contact_mail = contact_mail;
    }

    public Set<Long> getClassrooms_id() {
        return classrooms_id;
    }

    public void setClassrooms_id(Set<Long> classrooms_id) {
        this.classrooms_id = classrooms_id;
    }
}
