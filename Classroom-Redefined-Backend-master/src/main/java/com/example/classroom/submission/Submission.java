package com.example.classroom.submission;


import com.example.classroom.assignment.Assignment;
import com.example.classroom.file.File;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Submission {
    @Id
    private String id;

    private Timestamp submittedOn;

    private String information;

    private  String submittedBy;

    private float grade;

    @OneToMany
    private List<File> addedFiles;


    @ManyToOne
    private Assignment assignment;

}
