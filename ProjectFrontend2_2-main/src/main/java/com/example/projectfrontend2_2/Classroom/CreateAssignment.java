package com.example.projectfrontend2_2.Classroom;

import com.example.projectfrontend2_2.HelloApplication;
import com.example.projectfrontend2_2.http.RequestMaker;
import com.example.projectfrontend2_2.teacher.TeacherDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.time.*;
import java.util.*;

public class CreateAssignment {
    @FXML
    private Button files;

    @FXML
    private TextField title;

    @FXML
    private TextField instr;

    @FXML
    private TextField marks;

    @FXML
    private Button upload;


    @FXML
    private DatePicker dpkr;

    private LocalDate date;
    private Timestamp ts;

    private ClassroomDTO cdto;
    private TeacherDTO tdto;

    private List<String> filepaths = new ArrayList<>();

    private List<Long> fileID = new ArrayList<>();

    private RequestMaker rqm = new RequestMaker();
    @FXML
    private ListView listview;

    public void ButtonAction(ActionEvent event) throws URISyntaxException, IOException, InterruptedException {
        FileChooser fc = new FileChooser();

        List<File> selectedFiles = fc.showOpenMultipleDialog(null);

        if(selectedFiles != null){
            for(int i = 0; i < selectedFiles.size(); ++i){
                listview.getItems().add(selectedFiles.get(i).getName());
                filepaths.add(selectedFiles.get(i).getAbsolutePath());
                fileID.add(rqm.upload_file(selectedFiles.get(i).getAbsolutePath()));
            }

        }else{
            System.out.println("File Not Valid!");
        }
    }

    public void uploadClick() throws IOException, InterruptedException {

        AssignmentDTO adto = new AssignmentDTO();
        adto.setClassroomid(cdto.getId());
        adto.setDeadline(ts);
        adto.setTitle(title.getText());
        adto.setInstruction(instr.getText());
        adto.setNeededFilesID(fileID);
        adto.setMarks(Float.parseFloat(marks.getText()));

        rqm.create_assignment(adto);





        System.out.println(ts.toString());


    }


    @FXML
    public void datePickerClick(){
        date = dpkr.getValue();

        date = date.plusDays(1);

        Instant instant = Instant.from(date.atStartOfDay(ZoneId.systemDefault()));
        LocalDateTime dat = LocalDateTime.ofInstant(instant , ZoneOffset.systemDefault());


        dat = dat.minusSeconds(1);

        ts = Timestamp.valueOf(dat);

        System.out.println(ts);
    }
    public ClassroomDTO getCdto() {
        return cdto;
    }

    public void setCdto(ClassroomDTO cdto) {
        this.cdto = cdto;
    }

    public TeacherDTO getTdto() {
        return tdto;
    }

    public void setTdto(TeacherDTO tdto) {
        this.tdto = tdto;
    }

    public void goToHome(ActionEvent event) throws IOException {
        Node root = (Node) event.getSource();
        Stage myStage = (Stage) root.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene subtractionScene = new Scene(fxmlLoader.load());
        myStage.setScene(subtractionScene);
        myStage.show();
    }
}
