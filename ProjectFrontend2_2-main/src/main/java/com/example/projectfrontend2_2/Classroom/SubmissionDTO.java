package com.example.projectfrontend2_2.Classroom;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SubmissionDTO {
    private String id;


    private Timestamp submittedOn;

    private String information;

    private float grade;

    private List<Long> addedFiles = new ArrayList<>();

    private  Long assignmentId;

    private  String submittedBy;

    public String getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getSubmittedOn() {
        return submittedOn;
    }

    public void setSubmittedOn(Timestamp submittedOn) {
        this.submittedOn = submittedOn;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public List<Long> getAddedFiles() {
        return addedFiles;
    }

    public void setAddedFiles(List<Long> addedFiles) {
        this.addedFiles = addedFiles;
    }
}
