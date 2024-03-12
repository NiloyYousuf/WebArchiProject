package com.example.projectfrontend2_2;

import com.example.projectfrontend2_2.Classroom.AssignmentDTO;
import com.example.projectfrontend2_2.Classroom.ClassScene;
import com.example.projectfrontend2_2.Classroom.ClassroomDTO;
import com.example.projectfrontend2_2.Student.StudentDTO;
import com.example.projectfrontend2_2.courseReg.CourseRegistration;
import com.example.projectfrontend2_2.http.RequestMaker;
import com.example.projectfrontend2_2.post.PostDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class core {
    @FXML
    private ScrollPane scroll;

    @FXML
    private TextArea txtArea;

    public Label getTitle() {
        return title;
    }

    public void setTitle(Label title) {
        this.title = title;
    }

    @FXML
    private Label title;
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

    @FXML
    private int studentid;
    private String nm;


    private StudentDTO sdto;
    private ClassroomDTO cdto;
    private RequestMaker rqm  = new RequestMaker();
    public ClassroomDTO getCdto() {
        return cdto;
    }

    public void setCdto(ClassroomDTO cdto) {
        this.cdto = cdto;
    }

    public StudentDTO getSdto() {
        return sdto;
    }

    public void setSdto(StudentDTO sdto) {
        this.sdto = sdto;
    }

    public void initialize(){
        id.setText(" " + (studentid));
        name.setText(nm);
    }

    @FXML
    private void handleMouseEntered(MouseEvent event) {
        Node node = (Node) event.getSource();
        node.getStyleClass().add("tile-hover");
    }

    @FXML
    private void handleMouseExited(MouseEvent event) {
        Node node = (Node) event.getSource();
        node.getStyleClass().remove("tile-hover");
    }

    public  void init() throws IOException, InterruptedException {

        VBox vb = new VBox();
        vb.setSpacing(10);
        vb.setPadding(new Insets(0,0,0,5));
        cdto = rqm.fetch_classroom(cdto.getId());
        List<Long> classlist = new ArrayList<>();
        classlist.addAll(cdto.getPosts());
        Collections.sort(classlist , (a , b)->{
            return Math.toIntExact(b - a);
        });

        for(Long id : classlist){
            FXMLLoader fxl = new FXMLLoader(HelloApplication.class.getResource("tile.fxml"));
            Node e = fxl.load();
            tilecontrol tc = fxl.getController();
            PostDTO p = rqm.fetch_post(id);

            LocalDateTime lc = p.getTime().toLocalDateTime();
            tc.setP(p);
            tc.init();
            vb.getChildren().add(e);
            VBox.setVgrow(e, Priority.ALWAYS);
        }
        scroll.setContent(vb);
        title.setText(cdto.getCoursename());
    }



    @FXML
    public void create_post() throws IOException, InterruptedException {
        Date date = new Date();

        Timestamp ts = new Timestamp(date.getTime());
        PostDTO pdto = new PostDTO(Long.parseLong("0") ,txtArea.getText() , ts ,sdto.getName() , new ArrayList<>() , cdto.getId());

        rqm.create_post(pdto);
        cdto = rqm.fetch_classroom(cdto.getId());
        init();
    }

    public void goToSubmitAss(ActionEvent event) throws IOException {
        Node root = (Node) event.getSource();
        Stage myStage = (Stage) root.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AssSubmit.fxml"));
        Scene subtractionScene = new Scene(fxmlLoader.load());
        myStage.setScene(subtractionScene);
        myStage.show();
    }




    //FXMLLoader fxm = new FXMLLoader(HelloApplication.class.getResource("assignment_tile.fxml"));

    public  void init2() throws IOException, InterruptedException {

        VBox vb = new VBox();
        vb.setSpacing(10);
        vb.setPadding(new Insets(0,0,0,5));
        cdto = rqm.fetch_classroom(cdto.getId());

        List<Node> vbnodes = new ArrayList<>();
        for(Long x : cdto.getAssignmentsHereID()){
            FXMLLoader fxl = new FXMLLoader(HelloApplication.class.getResource("assignment_tile.fxml"));
            Node e = fxl.load();
            System.out.println("jo");
            assignment_tile_control astc = fxl.getController();
            astc.setAdto(rqm.fetch_ass(x));
            astc.setSdto(sdto);
            astc.init();
            e.setUserData(astc.getAdto());
            vbnodes.add(e);
        }

        Collections.sort(vbnodes , (a , b) ->{
            AssignmentDTO x = (AssignmentDTO) a.getUserData();
            AssignmentDTO y = (AssignmentDTO) b.getUserData();

            return x.getDeadline().compareTo(y.getDeadline());
        });
        vb.getChildren().addAll(vbnodes);
            //VBox.setVgrow(e, Priority.ALWAYS);
        scroll.setContent(vb);
    }


    public void onAssignmentClick() throws IOException, InterruptedException {
        cdto = rqm.fetch_classroom(cdto.getId());
        init2();
    }
    public void onPostClick() throws IOException, InterruptedException {
        cdto = rqm.fetch_classroom(cdto.getId());
        init();
    }
    public void goToHome(ActionEvent event) throws IOException {
        Node root = (Node) event.getSource();
        Stage myStage = (Stage) root.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene subtractionScene = new Scene(fxmlLoader.load());
        myStage.setScene(subtractionScene);
        myStage.show();
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


}
