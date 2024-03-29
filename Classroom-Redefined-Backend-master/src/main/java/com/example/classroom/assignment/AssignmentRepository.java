package com.example.classroom.assignment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment , Long> {

    List<Assignment> findByClassroomId(Long id);
}
