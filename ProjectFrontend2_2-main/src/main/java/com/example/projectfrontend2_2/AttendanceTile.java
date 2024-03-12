package com.example.projectfrontend2_2;

import com.example.projectfrontend2_2.Classroom.Attendance;
import com.example.projectfrontend2_2.Classroom.ClassroomDTO;
import com.example.projectfrontend2_2.Student.StudentDTO;
import com.example.projectfrontend2_2.http.RequestMaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

public class AttendanceTile {

    @FXML
    Label ID;

    @FXML
    RadioButton attendance;

    @FXML
    private  Button present;
    @FXML
    private Button absent;

    private StudentDTO sdto;

    private  ClassroomDTO cdto;

    private Date date;

    private Boolean isPresent;

    RequestMaker rqm = new RequestMaker();
    public void init(){
        ID.setText(sdto.getStudid().toString());
    }


    @FXML
    public  void radioClick(ActionEvent event) throws IOException, InterruptedException {

        boolean b = attendance.isSelected();
        Attendance as = new Attendance(date , cdto.getId() , sdto.getStudid() , b );
        rqm.create_attendance(as);
    }

    @FXML
    public  void presentClick(ActionEvent event) throws IOException, InterruptedException {

        present.setStyle("-fx-background-color: #38429c  ");
        absent.setStyle("-fx-background-color: #95ace8");
        Attendance as = new Attendance(date , cdto.getId() , sdto.getStudid() , true );
        rqm.create_attendance(as);
    }

    @FXML
    public  void absentClick(ActionEvent event) throws IOException, InterruptedException {

        present.setStyle("-fx-background-color:  #95ace8 ");
        absent.setStyle("-fx-background-color: #38429c");
        Attendance as = new Attendance(date , cdto.getId() , sdto.getStudid() , false );
        rqm.create_attendance(as);
    }

    public ClassroomDTO getCdto() {
        return cdto;
    }

    public void setCdto(ClassroomDTO cdto) {
        this.cdto = cdto;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getPresent() {
        return isPresent;
    }

    public void setPresent(Boolean present) {
        isPresent = present;
    }

    public StudentDTO getSdto() {
        return sdto;
    }

    public void setSdto(StudentDTO sdto) {
        this.sdto = sdto;
    }
}
