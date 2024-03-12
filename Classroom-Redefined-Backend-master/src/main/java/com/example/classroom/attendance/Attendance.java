package com.example.classroom.attendance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.sql.Date;


@Entity
@Getter
@Setter
@IdClass(AttendanceID.class)
public class Attendance {
    @Id
    @JsonFormat(pattern="yyyy-MM-dd")
    Date date;

    @Id
    Long classroomid;

    @Id
    Long studentid;

    Boolean isPresent;


}
