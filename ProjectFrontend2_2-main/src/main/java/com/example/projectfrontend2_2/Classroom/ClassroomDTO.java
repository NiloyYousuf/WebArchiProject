package com.example.projectfrontend2_2.Classroom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClassroomDTO {
    private Long id;
    private String dept;

    private  int coursecode;
    private int semester;

    private boolean archived;

    private int session;

    private String coursename;
    private Set<Long> students = new HashSet<>();


    private Set<Long> posts = new HashSet<>();


    private Long teacher;

    private List<Long> assignmentsHereID = new ArrayList<>();

    public List<Long> getAssignmentsHereID() {
        return assignmentsHereID;
    }

    public void setAssignmentsHereID(List<Long> assignmentsHereID) {
        this.assignmentsHereID = assignmentsHereID;
    }

    public Long getTeacher() {
        return teacher;
    }

    public void setTeacher(Long teacher) {
        this.teacher = teacher;
    }

    public Set<Long> getPosts() {
        return posts;
    }

    public void setPosts(Set<Long> posts) {
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public int getCoursecode() {
        return coursecode;
    }

    public void setCoursecode(int coursecode) {
        this.coursecode = coursecode;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public Set<Long> getStudents() {
        return students;
    }

    public void setStudents(Set<Long> students) {
        this.students = students;
    }

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }


    @Override
    public String toString(){
        return dept + " " + coursecode;
    }
}
