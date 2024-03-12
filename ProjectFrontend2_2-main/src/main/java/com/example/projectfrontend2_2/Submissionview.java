package com.example.projectfrontend2_2;

import com.example.projectfrontend2_2.Classroom.AssignmentDTO;
import com.example.projectfrontend2_2.Classroom.SubmissionDTO;
import com.example.projectfrontend2_2.teacher.TeacherDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Timestamp;

public class Submissionview {
    @FXML
    private Label name;

    private SubmissionDTO sub;

    private AssignmentDTO adto;
    private TeacherDTO tdto;

    @
    FXML
    private AnchorPane ancPane;



    public AssignmentDTO getAdto() {
        return adto;
    }

    public void setAdto(AssignmentDTO adto) {
        this.adto = adto;
    }

    public TeacherDTO getTdto() {
        return tdto;
    }

    public void setTdto(TeacherDTO tdto) {
        this.tdto = tdto;
    }

    public SubmissionDTO getSub() {
        return sub;
    }

    public void setSub(SubmissionDTO sub) {
        this.sub = sub;
    }

    public Label getName() {
        return name;
    }

    public void setName(Label name) {
        this.name = name;
    }



    public void init(){
        Timestamp cur_time = new Timestamp(System.currentTimeMillis());

        if(adto.getDeadline().compareTo(sub.getSubmittedOn()) > 0){
           ancPane.setStyle("-fx-background-color: #b5dfec");
        }
        else{
            ancPane.setStyle("-fx-background-color: #efabad");

        }
    }
    @FXML
    public void onGradeClick(javafx.event.ActionEvent event) throws IOException, InterruptedException {
        GradeScene.currentSub = sub;
        Node root = (Node)event.getSource();
        Stage myStage = (Stage) root.getScene().getWindow();
        FXMLLoader fxml = new FXMLLoader(HelloApplication.class.getResource("GradeScene.fxml"));
        Scene as = new Scene(fxml.load());
        GradeScene sb = fxml.getController();

        sb.setAdto(adto);
        sb.setTdto(tdto);
        sb.init();
        sb.init_sub();
        myStage.setScene(as);
        myStage.show();
    }
}
