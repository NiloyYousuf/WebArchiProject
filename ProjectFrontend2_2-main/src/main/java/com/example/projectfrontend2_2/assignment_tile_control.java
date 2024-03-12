package com.example.projectfrontend2_2;

import com.example.projectfrontend2_2.Classroom.AssignmentDTO;
import com.example.projectfrontend2_2.Classroom.SubmitAss;
import com.example.projectfrontend2_2.Classroom.SubmitAssignment;
import com.example.projectfrontend2_2.Student.StudentDTO;
import com.example.projectfrontend2_2.teacher.TeacherDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class assignment_tile_control {
    @FXML
    Label teacher;
    @FXML
    Label date;

    private StudentDTO sdto;

    private TeacherDTO tdto;
    private AssignmentDTO adto;

    private boolean isTeach = false;

    public boolean isTeach() {
        return isTeach;
    }

    public void setTeach(boolean teach) {
        isTeach = teach;
    }

    public StudentDTO getSdto() {
        return sdto;
    }


    public TeacherDTO getTdto() {
        return tdto;
    }

    public void setTdto(TeacherDTO tdto) {
        this.tdto = tdto;
    }

    public void setSdto(StudentDTO sdto) {
        this.sdto = sdto;
    }

    public AssignmentDTO getAdto() {
        return adto;
    }

    public void setAdto(AssignmentDTO adto) {
        this.adto = adto;
    }

    public Label getTeacher() {
        return teacher;
    }

    public void setTeacher(Label teacher) {
        this.teacher = teacher;
    }

    public Label getDate() {
        return date;
    }

    public void setDate(Label date) {
        this.date = date;
    }

    public Label getAssignment_title() {
        return assignment_title;
    }

    public void setAssignment_title(Label assignment_title) {
        this.assignment_title = assignment_title;
    }

    @FXML
    Label assignment_title;

    @FXML
    private  Label duetxt;

    @FXML
    private AnchorPane duepane;

    @FXML
    private void onItemPressed() throws IOException, InterruptedException {

        if(isTeach){
            Stage myStage = new Stage();
            FXMLLoader fxml = new FXMLLoader(HelloApplication.class.getResource("GradeScene.fxml"));
            Scene as = new Scene(fxml.load());
            GradeScene sb = fxml.getController();

            sb.setAdto(adto);
            sb.setTdto(tdto);

            sb.init();
            myStage.setScene(as);
            myStage.show();
        }
        else{
            Stage myStage = new Stage();
            FXMLLoader fxml = new FXMLLoader(HelloApplication.class.getResource("AssSubmit.fxml"));
            Scene as = new Scene(fxml.load());
            SubmitAss sb = fxml.getController();

            sb.setSdto(sdto);
            sb.setAdto(adto);
            sb.init();
            myStage.setScene(as);
            myStage.show();
        }

    }

    public void init() {
        teacher.setText(adto.getTitle());
        date.setText(adto.getDeadline().toLocalDateTime().format(DateTimeFormatter.ofPattern("d LLLL uuuu , hh:mm:ss a")));
        assignment_title.setText(adto.getInstruction());


        LocalDateTime s = LocalDateTime.now();
        LocalDateTime d = adto.getDeadline().toLocalDateTime();

        if(0 > d.compareTo(s)){
            duetxt.setText("0 hours");
            duepane.setStyle("-fx-background-color:  #FCC2A7 ; -fx-background-radius : 3 ;-fx-border-radius :3 ; -fx-border-color: black ; -fx-border-width: 1");
            long days = ChronoUnit.DAYS.between(s , d);
            System.out.println("days" + days);
        }
        else{
            duepane.setStyle("-fx-background-color:  #9bb5ea ; -fx-background-radius : 3 ;-fx-border-radius :3 ; -fx-border-color: black ; -fx-border-width: 1 ");
           long days = ChronoUnit.DAYS.between(s , d);
           long months = ChronoUnit.MONTHS.between(s ,d);
           long hours = ChronoUnit.HOURS.between(s,d);

            duetxt.setText(hours + " Hours");

            if (days >= 1){
                duetxt.setText(days + " Days");
            }
           if(days > 30){
               duetxt.setText(months + " Months");
           }




           System.out.println("days" + days);
        }
    }
}
