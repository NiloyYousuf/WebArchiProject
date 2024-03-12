package com.example.classroom.controllers;



import com.example.classroom.classroom.ClassroomRepository;
import com.example.classroom.login.LoginDTO;
import com.example.classroom.student.Student;
import com.example.classroom.student.StudentDTO;
import com.example.classroom.student.StudentRepository;
import com.example.classroom.teacher.Teacher;
import com.example.classroom.teacher.TeacherDTO;
import com.example.classroom.teacher.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private StudentRepository stud;

    @Autowired
    private ClassroomRepository clas;

    @Autowired
    private TeacherRepository teachers;
    @PostMapping("/student")
    @ResponseBody
    public StudentDTO student_login(@RequestBody LoginDTO ldto){
        StudentDTO sdto = new StudentDTO();

        sdto.setStudid((long) -1);


        Optional<Student> sopt = stud.findStudentByStudid(ldto.getCommon_id());



        if(sopt.isPresent()){
            Student s = sopt.get();;

            if(sopt.get().getPassword().equals(ldto.getPassword())){

                sdto = new StudentDTO(sopt.get());
            }
        }
        return sdto;
    }


    @PostMapping("/teacher")
    @ResponseBody
    public TeacherDTO teacher_login(@RequestBody LoginDTO ldto){
        TeacherDTO tdto = new TeacherDTO();

        tdto.setTeachid((long) -1);


        Optional<Teacher> topt = teachers.findTeacherByTeachid(ldto.getCommon_id());



        if(topt.isPresent()){
            Teacher s = topt.get();;

            if(topt.get().getPassword().equals(ldto.getPassword())){

                tdto = new TeacherDTO(s);
            }
        }
        return tdto;
    }


}
