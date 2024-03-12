package com.example.classroom.controllers;


import com.example.classroom.classroom.Classroom;
import com.example.classroom.classroom.ClassroomDTO;
import com.example.classroom.classroom.ClassroomRepository;
import com.example.classroom.email.EmailSender;
import com.example.classroom.login.CourseRegDTO;
import com.example.classroom.login.LoginDTO;
import com.example.classroom.student.Student;
import com.example.classroom.student.StudentDTO;
import com.example.classroom.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.*;

@RestController
@RequestMapping("/fetch")
public class PreClassroomController {

    @Autowired
    ClassroomRepository clas;

    @Autowired
    StudentRepository stud;
    @GetMapping("/classroom/{id}")
    @ResponseBody
    public ClassroomDTO fetch_class(@PathVariable Long id){
        Optional<Classroom> copt = clas.findById(id);
        ClassroomDTO cdto = new ClassroomDTO();
        cdto.setId((long)-1);
        if(copt.isPresent()){
            Classroom c = copt.get();
//            if(c.getStudents().isEmpty()){
//                copt.get().getStudents().addAll(stud.findStudentsByDept(c.getDept()));
//                clas.save(copt.get());
//            }
            cdto = new ClassroomDTO(copt.get());
        }
        return cdto;
    }

    @GetMapping("/student/{id}")
    @ResponseBody
    public StudentDTO student_fetch(@PathVariable Long id){
        StudentDTO sdto = new StudentDTO();

        sdto.setStudid((long) -1);


        Optional<Student> sopt = stud.findById(id);


        if(sopt.isPresent()){
            sdto = new StudentDTO(sopt.get());
        }


        return sdto;
    }

    @GetMapping("/classroom/all")
    @ResponseBody
    public List<ClassroomDTO> fetch_all_class(){
        List<ClassroomDTO> class_list_id = new ArrayList<>();
        List<Classroom> class_list = new ArrayList<>();
        class_list.addAll(clas.findAll());
        for(Classroom x : class_list){
            class_list_id.add(new ClassroomDTO(x));
        }
        return class_list_id;
    }


    @PostMapping("/classroom/register")
    @ResponseBody
    public String register_class(@RequestBody CourseRegDTO crdto){
        Optional<Classroom> classroom = clas.findById(crdto.getClass_id());

        if(!classroom.isPresent()) return "Painai";

        Optional<Student> temp_student = stud.findStudentByStudid(crdto.getStud_id());
        if(!temp_student.isPresent()) return "Painai";

        Classroom temp_cls = classroom.get();
        temp_cls.getStudents().add(temp_student.get());
        clas.save(temp_cls);



        String text = "You have been registered to " + temp_cls.getCoursename() +".";
        List<String> mail_students = new ArrayList<>();

        mail_students.add(temp_student.get().getEmail());

        EmailSender es = new EmailSender(mail_students , text , "Course Registration" );
        Thread thread = new Thread(es);
        thread.start();


        return "Paisi";
    }
}
