package com.example.classroom.classroom;

import com.example.classroom.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClassroomRepository extends JpaRepository<Classroom , Long> {


    List<Classroom> findClassroomsByStudents(Student k);
    List<Classroom> findClassroomsByDeptAndSemesterAndArchived(String dept ,int semester , boolean archived);


}
