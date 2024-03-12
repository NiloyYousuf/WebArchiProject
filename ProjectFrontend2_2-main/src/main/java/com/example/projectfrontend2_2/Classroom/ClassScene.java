package com.example.projectfrontend2_2.Classroom;

import com.example.projectfrontend2_2.Student.StudentDTO;
import com.example.projectfrontend2_2.courseReg.CourseRegistration;
import com.example.projectfrontend2_2.HelloApplication;
import com.example.projectfrontend2_2.http.RequestMaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassScene {
    @FXML
    private ComboBox<ClassroomDTO> courses;

    private StudentDTO studentDTO;
    private List<ClassroomDTO> all_classrooms;

    @FXML
    private Label name;
    @FXML
    private Label id;

    @FXML
    private Label coursename;
    @FXML
    private Label txt;

    @FXML
    private Label attendance_link;
    private int ass = 0 , studentid;
    private String nm;


    public List<ClassroomDTO> getAll_classrooms() {
        return all_classrooms;
    }

    public void setAll_classrooms(List<ClassroomDTO> all_classrooms) {
        this.all_classrooms = all_classrooms;
    }

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

    @FXML
    private Label semester;
    @FXML
    private Label post;

    @FXML
    private Button assignment;

    @FXML
    private Button go;

    @FXML
    private Label due_ass;
    public void init() throws IOException, InterruptedException {
        RequestMaker rqm = new RequestMaker();
        this.studentDTO = rqm.fetch_student(studentDTO.getId());
        id.setText(" " + studentDTO.getStudid());
        name.setText(studentDTO.getName());
        all_classrooms = new ArrayList<>();
        for(Long x : studentDTO.getClassroom_id()){
            all_classrooms.add(rqm.fetch_classroom(x));
        }


    }
    @FXML
    protected void onCourseClick(){
        //Getting the course name that is selected
        ClassroomDTO selected_course = courses.getValue();
        txt.setText(selected_course.toString());


        due_ass.setText("Assignments Due: " + ass);
        if(ass > 0){
            assignment.setText("Assignments Pending!");
            assignment.setTextFill(Color.RED);
        }
        else assignment.setText("No Assignments");


    }

    @FXML
    protected void goToRoutine(ActionEvent event) throws IOException{
        Node root = (Node) event.getSource();
        Stage myStage = (Stage) root.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Routine.fxml"));
        Scene x = new Scene(fxmlLoader.load(), 800, 600);
        myStage.setScene(x);
        myStage.show();
    }
    public void goToClass2(ActionEvent event) throws IOException {
        Node root = (Node) event.getSource();
        Stage myStage = (Stage) root.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ClassScene2.fxml"));
        Scene subtractionScene = new Scene(fxmlLoader.load());

        ClassScene2 clas2ctrl = fxmlLoader.getController();

        clas2ctrl.setSdto(studentDTO);
        clas2ctrl.setAll_classrooms(all_classrooms);

        clas2ctrl.setNm(studentDTO.getName());
        clas2ctrl.setStudentid(studentDTO.getStudid().intValue());
        clas2ctrl.initialize();

        myStage.setScene(subtractionScene);
        myStage.show();
    }
    public void goToHome(ActionEvent event) throws IOException {
        Node root = (Node) event.getSource();
        Stage myStage = (Stage) root.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene subtractionScene = new Scene(fxmlLoader.load());
        myStage.setScene(subtractionScene);
        myStage.show();
    }

    public void goToCourseReg(ActionEvent event) throws IOException, InterruptedException {
        Node root = (Node) event.getSource();
        Stage myStage = (Stage) root.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("course-registration.fxml"));
        Scene subtractionScene = new Scene(fxmlLoader.load());
        CourseRegistration crg = fxmlLoader.getController();
        crg.setCurrent_student(Integer.toUnsignedLong(this.studentid));

        crg.setSdto(studentDTO);
        crg.setNm(studentDTO.getName());
        crg.setStudentid(studentDTO.getStudid().intValue());


        crg.initialize();

        myStage.setScene(subtractionScene);
        myStage.show();
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }
}
