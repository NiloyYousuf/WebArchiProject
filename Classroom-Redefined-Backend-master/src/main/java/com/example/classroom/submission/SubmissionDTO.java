package com.example.classroom.submission;


import com.example.classroom.file.File;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class SubmissionDTO {
    private String id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp submittedOn;

    private String information;

    private float grade;

    private List<Long> addedFiles = new ArrayList<>();

    private  String submittedBy;
    private  Long assignmentId;
    public SubmissionDTO(Submission s) {
        this.id = s.getId();
        this.submittedOn = s.getSubmittedOn();
        this.information = s.getInformation();
        this.submittedBy = s.getSubmittedBy();
        this.assignmentId = s.getAssignment().getId();
        for(File x : s.getAddedFiles()){
            addedFiles.add(x.getId());
        }
    }


}
