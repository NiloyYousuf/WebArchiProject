package com.example.projectfrontend2_2.Classroom;

import com.example.projectfrontend2_2.FileView;
import com.example.projectfrontend2_2.HelloApplication;
import com.example.projectfrontend2_2.Student.StudentDTO;
import com.example.projectfrontend2_2.assignment_tile_control;
import com.example.projectfrontend2_2.http.RequestMaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SubmitAss {
    @FXML
    private Button files;

    @FXML
    private Button upload;

    @FXML
    private Label title;

    @FXML
    private Label todo;

    @FXML
    private ScrollPane files_pane;


    private StudentDTO sdto;
    private AssignmentDTO adto;

    private SubmissionDTO subdto;


    public StudentDTO getSdto() {
        return sdto;
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

    @FXML
    private ListView listview;
    private List<Long> neededFiles = new ArrayList<>();

    private RequestMaker rqm = new RequestMaker();
    @FXML
    private TextField comments;
    @FXML
    private Label submt;




    public void init() throws IOException, InterruptedException {
        title.setText(adto.getTitle());
        todo.setText(adto.getInstruction());
        subdto = rqm.fetch_submission_info(sdto.getStudid().toString() + adto.getId().toString());
        VBox vb = new VBox();
        vb.setSpacing(7);
        //vb.setPadding(new Insets(7,0,0,0));
        for(Long fid : adto.getNeededFilesID()){
            FXMLLoader fxl = new FXMLLoader(HelloApplication.class.getResource("FileView.fxml"));
            Node e = fxl.load();
            FileView tc = fxl.getController();
            FileDTO fdto = rqm.fetch_file_info(fid);

            tc.setFdto(fdto);
            tc.getFilename().setText(fdto.getFilename());
            tc.getDate().setText(fdto.getTs().toLocalDateTime().format(DateTimeFormatter.ofPattern("d LLL uuuu , HH:mm:ss")));

            vb.getChildren().add(e);
            VBox.setVgrow(e, Priority.ALWAYS);
        }

        files_pane.setContent(vb);

        System.out.println(subdto.getId());

        if(subdto.getId() == null){
            Timestamp current = new Timestamp(System.currentTimeMillis());
            if(adto.getDeadline().compareTo(current) < 0){
                submt.setText("Deadline Past");
                submt.setStyle("-fx-text-fill: #7c0a0a");
            }
            else{

                submt.setText("Not Submitted , Due " + adto.getDeadline().toLocalDateTime().format(DateTimeFormatter.ofPattern("d LLL uuuu , hh:mm:ss a")));
                submt.setStyle("-fx-text-fill: #7c0a0a");

            }

        }
        else {
            Timestamp current = new Timestamp(System.currentTimeMillis());

            if(adto.getDeadline().compareTo(current) < 0){
                submt.setText("Deadline Past");
                submt.setStyle("-fx-text-fill: #7c0a0a");
            }
            else{
                submt.setText("Submitted \nDeadline: " + adto.getDeadline().toLocalDateTime().format(DateTimeFormatter.ofPattern("d LLL uuuu , hh:mm:ss a")));
                submt.setStyle("-fx-text-fill: #227a2a");

            }

        }

        comments.setText(subdto.getInformation());
        listview.getItems().clear();
        for(Long sub_files : subdto.getAddedFiles()){
            listview.getItems().add(rqm.fetch_file_info(sub_files).getFilename());
        }
    }

    public void ButtonAction(ActionEvent event) throws URISyntaxException, IOException, InterruptedException {
        FileChooser fc = new FileChooser();

        List<File> selectedFiles = fc.showOpenMultipleDialog(null);

        if(selectedFiles != null){
            for(int i = 0; i < selectedFiles.size(); ++i){
                listview.getItems().add(selectedFiles.get(i).getName());
                neededFiles.add(rqm.upload_file(selectedFiles.get(i).getAbsolutePath()));


            }

        }else{
            System.out.println("File Not Valid!");
        }
    }


    @FXML
    public void upload_ass() throws IOException, InterruptedException {

        if(subdto == null){
            subdto = new SubmissionDTO();
        }
        subdto.setId(sdto.getStudid().toString() + adto.getId() );
        subdto.setGrade(-1);
        subdto.getAddedFiles().addAll(neededFiles);
        subdto.setInformation(comments.getText());
        subdto.setSubmittedBy(sdto.getName());
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        subdto.setSubmittedOn(ts);
        subdto.setAssignmentId(adto.getId());
        rqm.create_submit(subdto);
        init();

    }
}
