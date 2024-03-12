package com.example.classroom.student;

import com.example.classroom.classroom.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface StudentRepository extends JpaRepository<Student , Long> {
        Optional<Student> findStudentByStudid(Long studid);
        List<Student> findStudentsByDept(String dept);

        List<Student> findStudentsByDeptEqualsIgnoreCaseAndSemester(String dept , int semester);

        List<Student> findByClassroomsIn(Set<Classroom> classroom);

}
