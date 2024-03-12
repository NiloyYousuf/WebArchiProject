package com.example.projectfrontend2_2;

import com.example.projectfrontend2_2.Classroom.AssignmentDTO;
import com.example.projectfrontend2_2.Classroom.SubmissionDTO;
import com.example.projectfrontend2_2.Student.StudentDTO;
import com.example.projectfrontend2_2.http.RequestMaker;
import com.example.projectfrontend2_2.post.PostDTO;
import com.example.projectfrontend2_2.teacher.TeacherDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class GradeScene {

    @FXML
    private ScrollPane submissions;
    @FXML
    private ScrollPane files;

    @FXML
    private TextField grade;



    @FXML
    private Label assignment_name;
    @FXML
    private  Label student_name;
    @FXML
    private Label total_grade;

    private AssignmentDTO adto;

    static SubmissionDTO currentSub;

    private TeacherDTO tdto;

    private RequestMaker rqm = new RequestMaker();

    public  void init() throws IOException, InterruptedException {


        VBox vb = new VBox();
        vb.setSpacing(5);
        total_grade.setText(adto.getMarks().toString());
        assignment_name.setText(adto.getTitle());
        for(Long x  : adto.getSubmissionsOfThisID()){
            FXMLLoader fxl = new FXMLLoader(HelloApplication.class.getResource("submissionview.fxml"));
            Node e = fxl.load();
            Submissionview tc = fxl.getController();
            SubmissionDTO subdto = rqm.fetch_submission_info(x.toString());
            tc.getName().setText(subdto.getSubmittedBy());
            tc.setSub(subdto);
            tc.setAdto(adto);
            tc.setTdto(tdto);
            tc.init();
            vb.getChildren().add(e);
            VBox.setVgrow(e, Priority.ALWAYS);
        }

        submissions.setContent(vb);





    }

    @FXML
    public  void submit_grade() throws IOException, InterruptedException {

        currentSub.setGrade(Float.parseFloat(grade.getText()));

        rqm.create_submit(currentSub);
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
    public void init_sub() throws IOException, InterruptedException {
        student_name.setText(currentSub.getSubmittedBy());
        grade.setText(Float.toString(currentSub.getGrade()));

        VBox vb2 = new VBox();
        vb2.setSpacing(5);
        for(Long x : currentSub.getAddedFiles()){
            FXMLLoader fxl = new FXMLLoader(HelloApplication.class.getResource("FileView.fxml"));
            Node e = fxl.load();
            FileView tc = fxl.getController();

            tc.setFdto(rqm.fetch_file_info(x));
            tc.getFilename().setText(tc.getFdto().getFilename());
            tc.getDate().setText(tc.getFdto().getTs().toLocalDateTime().format(DateTimeFormatter.ofPattern("d LLL uuuu , hh:mm:ss a")));
            vb2.getChildren().add(e);
            VBox.setVgrow(e, Priority.ALWAYS);
        }

        files.setContent(vb2);
    }

    public ScrollPane getSubmissions() {
        return submissions;
    }

    public void setSubmissions(ScrollPane submissions) {
        this.submissions = submissions;
    }

    public ScrollPane getFiles() {
        return files;
    }

    public void setFiles(ScrollPane files) {
        this.files = files;
    }

    public TextField getGrade() {
        return grade;
    }

    public void setGrade(TextField grade) {
        this.grade = grade;
    }

    public Label getAssignment_name() {
        return assignment_name;
    }

    public void setAssignment_name(Label assignment_name) {
        this.assignment_name = assignment_name;
    }

    public Label getStudent_name() {
        return student_name;
    }

    public void setStudent_name(Label student_name) {
        this.student_name = student_name;
    }

    public Label getTotal_grade() {
        return total_grade;
    }

    public void setTotal_grade(Label total_grade) {
        this.total_grade = total_grade;
    }

    public AssignmentDTO getAdto() {
        return adto;
    }

    public void setAdto(AssignmentDTO adto) {
        this.adto = adto;
    }

    public static SubmissionDTO getCurrentSub() {
        return currentSub;
    }

    public static void setCurrentSub(SubmissionDTO currentSub) {
        GradeScene.currentSub = currentSub;
    }

    public TeacherDTO getTdto() {
        return tdto;
    }

    public void setTdto(TeacherDTO tdto) {
        this.tdto = tdto;
    }

    public RequestMaker getRqm() {
        return rqm;
    }

    public void setRqm(RequestMaker rqm) {
        this.rqm = rqm;
    }
}
