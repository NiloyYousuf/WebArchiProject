package com.example.projectfrontend2_2.Classroom;

import com.example.projectfrontend2_2.HelloApplication;
import com.example.projectfrontend2_2.Student.StudentDTO;
import com.example.projectfrontend2_2.core;
import com.example.projectfrontend2_2.core2;
import com.example.projectfrontend2_2.http.RequestMaker;
import com.example.projectfrontend2_2.teacher.TeacherDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class TeacherScene2 {
    @FXML
    private ComboBox<ClassroomDTO> courses;
    private ClassroomDTO course2;

    private TeacherDTO tdto;


    private RequestMaker rqm = new RequestMaker();





    @FXML
    private Label txt;




    @FXML
    private Label course;
    @FXML
    private Label teacher;


    @FXML
    private Label due_ass;

    @FXML
    private Label name;
    @FXML
    private Label id;
    private String nm;

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public int getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }

    private int teacherid;


    public TeacherDTO getTdto() {
        return tdto;
    }

    public void setTdto(TeacherDTO tdto) {
        this.tdto = tdto;
    }


    public  void init() throws IOException, InterruptedException {
        Set<Long> classes = tdto.getClassrooms_id();
        courses.getItems().clear();
        for(Long x : classes){
            courses.getItems().add(rqm.fetch_classroom(x));
        }
        id.setText(" " + (teacherid));
        name.setText(nm);
    }
    @FXML
    protected void onCourseClick(){
        //Getting the course name that is selected
        ClassroomDTO selected_course = courses.getValue();
        txt.setText(selected_course.toString());
        course2 = selected_course;

       course.setText(selected_course.getCoursename());
       teacher.setText("Mr. X , Designation");
       due_ass.setText("Nothing due");



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

    public void goToHome(ActionEvent event) throws IOException {
        Node root = (Node) event.getSource();
        Stage myStage = (Stage) root.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene subtractionScene = new Scene(fxmlLoader.load());
        myStage.setScene(subtractionScene);
        myStage.show();
    }


    @FXML
    public void goToCore2(ActionEvent event) throws IOException, InterruptedException {
        Node root = (Node) event.getSource();
        Stage myStage = (Stage) root.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("scrolscrene2.fxml"));
        Scene subtractionScene = new Scene(fxmlLoader.load());
        core2 obj = fxmlLoader.getController();
        obj.setCdto(course2);
        obj.setSdto(tdto);
        System.out.println(tdto.getName());
        obj.init();
        myStage.setScene(subtractionScene);
        myStage.show();
    }

}
