package com.example.projectfrontend2_2.courseReg;

import com.example.projectfrontend2_2.Classroom.ClassScene;
import com.example.projectfrontend2_2.Classroom.ClassroomDTO;
import com.example.projectfrontend2_2.HelloApplication;
import com.example.projectfrontend2_2.Student.StudentDTO;
import com.example.projectfrontend2_2.http.RequestMaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseRegistration {

    @FXML
    private ComboBox<ClassroomDTO> courses;

    private  ClassroomDTO selected_course; // selected classroom to register

    public StudentDTO getSdto() {
        return sdto;
    }

    public void setSdto(StudentDTO sdto) {
        this.sdto = sdto;
    }

    private StudentDTO sdto;

    private Long current_student;



    @FXML
    private Label txt;




    @FXML
    private Label course;
    @FXML
    private Label teacher;
    private RequestMaker rqm= new RequestMaker();

    @FXML
    private Label due_ass;

    @FXML
    private Label name;
    @FXML
    private Label id;

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }


    private int studentid;
    private String nm;

    public void initialize() throws IOException, InterruptedException {

        List<ClassroomDTO> classroomDTOS = rqm.fetch_all_classroom() ;

        courses.getItems().clear();
        for(ClassroomDTO x : classroomDTOS){
            courses.getItems().add(x);
        }
        id.setText(" " + (studentid));
        name.setText(nm);


    }
    @FXML
    protected void onCourseClick(){
        //Getting the course  that is selected

        selected_course = courses.getValue();
        txt.setText(selected_course.toString());
        course.setText(selected_course.getCoursename());
        teacher.setText("Mr. X , Designation");
        due_ass.setText("Nothing due");



    }

    @FXML
    public void onRegisterClick() throws IOException, InterruptedException {
        CourseRegDTO crdto = new CourseRegDTO(selected_course.getId() , sdto.getStudid());

        rqm.course_register_attempt(crdto);
    }



    public void goToHome(ActionEvent event) throws IOException {
        Node root = (Node) event.getSource();
        Stage myStage = (Stage) root.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene subtractionScene = new Scene(fxmlLoader.load());
        myStage.setScene(subtractionScene);
        myStage.show();
    }

    public Long getCurrent_student() {
        return current_student;
    }

    public void goToHome2(ActionEvent event) throws IOException, InterruptedException {
        Node root = (Node) event.getSource();
        Stage myStage = (Stage) root.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ClassScene.fxml"));
        Scene subtractionScene = new Scene(fxmlLoader.load());

        ClassScene cs = fxmlLoader.getController();

        cs.setStudentDTO(sdto);
        cs.init();
        myStage.setScene(subtractionScene);
        myStage.show();
    }

    public void setCurrent_student(Long current_student) {
        this.current_student = current_student;
    }
}
