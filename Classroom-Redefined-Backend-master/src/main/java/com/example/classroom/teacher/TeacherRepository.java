package com.example.classroom.teacher;

import com.example.classroom.classroom.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface TeacherRepository extends JpaRepository<Teacher , Long> {

    Optional<Teacher> findTeacherByTeachid(Long teach_id);


}
