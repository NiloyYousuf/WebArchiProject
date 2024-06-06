package com.example.classroom.attendance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance , AttendanceID> {
    List<Attendance> findByClassroomidAndDate(Long classroomId, Date date);

    long countAttendanceByClassroomid(Long ClassroomID);
    long countAttendanceByClassroomidAndStudentid(Long ClassroomID , Long StudentID);
    long countAttendanceByClassroomidAndStudentidAndIsPresent(Long ClassroomID , Long StudentID ,Boolean isPresent);

}
