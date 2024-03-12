package com.example.projectfrontend2_2.Classroom;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDTO {

    private Long id;



    private Timestamp deadline;

    private String title;
    private String instruction;

    private Float marks;


    private List<Long> neededFilesID = new ArrayList<>();


    private  List<Long> submissionsOfThisID = new ArrayList<>();

    private Long classroomid;


    public Long getClassroomid() {
        return classroomid;
    }

    public void setClassroomid(Long classroomid) {
        this.classroomid = classroomid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Float getMarks() {
        return marks;
    }

    public void setMarks(Float marks) {
        this.marks = marks;
    }

    public List<Long> getNeededFilesID() {
        return neededFilesID;
    }

    public void setNeededFilesID(List<Long> neededFilesID) {
        this.neededFilesID = neededFilesID;
    }

    public List<Long> getSubmissionsOfThisID() {
        return submissionsOfThisID;
    }

    public void setSubmissionsOfThisID(List<Long> submissionsOfThisID) {
        this.submissionsOfThisID = submissionsOfThisID;
    }
}
