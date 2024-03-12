package com.example.classroom.assignment;


import com.example.classroom.classroom.Classroom;
import com.example.classroom.file.File;
import com.example.classroom.submission.Submission;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp deadline;

    private String title;
    private String instruction;

    private Float marks;

    @OneToMany
    private List<File> neededFiles;

    @OneToMany(mappedBy = "assignment")
    private  List<Submission> submissionsOfThis;


    @ManyToOne
    private Classroom classroom;
}
