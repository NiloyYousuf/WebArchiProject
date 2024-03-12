package com.example.projectfrontend2_2.Classroom;

import com.example.projectfrontend2_2.HelloApplication;
import com.example.projectfrontend2_2.teacher.TeacherDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class TeacherScene {


    private TeacherDTO tdto;


    @FXML
    private Label name;
    @FXML
    private Label id;


    public TeacherDTO getTdto() {
        return tdto;
    }

    public void setTdto(TeacherDTO tdto) {
        this.tdto = tdto;
    }

    public void init(){

        System.out.println("ts init");
        if(tdto == null) {
            System.out.println("NULL");
            return;
        }

        id.setText(" " + tdto.getTeachid());
        name.setText(tdto.getName());
    }


    @FXML
    protected void goToRoutine(ActionEvent event) throws IOException {
        Node root = (Node) event.getSource();
        Stage myStage = (Stage) root.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Routine.fxml"));
        Scene x = new Scene(fxmlLoader.load());
        myStage.setScene(x);
        myStage.show();
    }

    @FXML
    public void goToClass2(ActionEvent event) throws IOException, InterruptedException {
        Node root = (Node) event.getSource();
        Stage myStage = (Stage) root.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TeacherScene2.fxml"));
        Scene subtractionScene = new Scene(fxmlLoader.load());

        TeacherScene2 clas2ctrl = fxmlLoader.getController();

        //clas2ctrl.setAll_classrooms(all_classrooms);
        clas2ctrl.setTdto(tdto);
        clas2ctrl.setNm(tdto.getName());
        clas2ctrl.setTeacherid(tdto.getTeachid().intValue());
        clas2ctrl.init();
        myStage.setScene(subtractionScene);
        myStage.show();
    }

    @FXML
    public void goToHome(ActionEvent event) throws IOException {
        Node root = (Node) event.getSource();
        Stage myStage = (Stage) root.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene subtractionScene = new Scene(fxmlLoader.load());
        myStage.setScene(subtractionScene);
        myStage.show();
    }


}
