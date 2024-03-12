package com.example.classroom.classroom;


import com.example.classroom.assignment.Assignment;
import com.example.classroom.post.Post;
import com.example.classroom.student.Student;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
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

    public  ClassroomDTO(Classroom clss){
        this.id = clss.getId();
        this.dept = clss.getDept();
        this.coursecode = clss.getCoursecode();
        this.semester = clss.getSemester();
        this.coursename = clss.getCoursename();;
        this.session = clss.getSession();
        this.teacher = clss.getTeacher().getId();
        System.out.println(clss.getStudents().isEmpty());
        for (Student x: clss.getStudents()) {

            students.add(x.getId());

        }

        for(Post p : clss.getPosts()){
            posts.add(p.getId());
        }

        for(Assignment p : clss.getAssignmentsHere()){
            assignmentsHereID.add(p.getId());
        }


    }
}
