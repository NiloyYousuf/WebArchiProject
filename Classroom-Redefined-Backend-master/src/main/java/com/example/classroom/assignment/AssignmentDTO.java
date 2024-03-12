package com.example.classroom.assignment;


import com.example.classroom.classroom.Classroom;
import com.example.classroom.file.File;
import com.example.classroom.submission.Submission;
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
public class AssignmentDTO {

    private Long id;


    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp deadline;

    private String title;
    private String instruction;

    private Float marks;


    private List<Long> neededFilesID = new ArrayList<>();


    private  List<String> submissionsOfThisID = new ArrayList<>();

    private Long classroomid;

    public AssignmentDTO(Assignment ass) {
        this.id = ass.getId();
        this.deadline = ass.getDeadline();
        this.title = ass.getTitle();
        this.instruction = ass.getInstruction();
        this.marks = ass.getMarks();
        this.classroomid = ass.getClassroom().getId();
        for(File x : ass.getNeededFiles()){
            neededFilesID.add(x.getId());
        }
        for(Submission x : ass.getSubmissionsOfThis()){
            submissionsOfThisID.add(x.getId());
        }

    }
}
