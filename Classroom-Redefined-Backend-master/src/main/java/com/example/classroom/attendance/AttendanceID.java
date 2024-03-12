package com.example.classroom.attendance;

import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;


@NoArgsConstructor
public class AttendanceID implements Serializable {

    Date date;
    Long classroomid;
    Long studentid;

    public AttendanceID(Date date, Long classroomID, Long studentID) {
        this.date = date;
        this.classroomid = classroomID;
        this.studentid = studentID;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {

        Long l2 = classroomid + studentid;
        return l2.hashCode();
    }
}
