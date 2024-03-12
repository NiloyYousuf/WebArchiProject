package com.example.projectfrontend2_2;

import com.example.projectfrontend2_2.Classroom.AssignmentDTO;
import com.example.projectfrontend2_2.Classroom.ClassroomDTO;
import com.example.projectfrontend2_2.Classroom.CreateAssignment;
import com.example.projectfrontend2_2.Classroom.TeacherScene2;
import com.example.projectfrontend2_2.Student.StudentDTO;
import com.example.projectfrontend2_2.courseReg.CourseRegistration;
import com.example.projectfrontend2_2.http.RequestMaker;
import com.example.projectfrontend2_2.post.PostDTO;
import com.example.projectfrontend2_2.teacher.TeacherDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class core2 {
    @FXML
    private ScrollPane scroll;

    @FXML
    private TextArea txtArea;


    @FXML
    private TextField link_text;

    @FXML
    private ListView link_list;

    @FXML
    private Button assign;

    @FXML
    private Button back;

    @FXML
    private Button attendance;

    @FXML

    private Label name;

    @FXML
    private  Label id;

    @FXML
    private Label title;

    private TeacherDTO tdto;
    private ClassroomDTO cdto;
    private RequestMaker rqm  = new RequestMaker();
    public ClassroomDTO getCdto() {
        return cdto;
    }

    public void setCdto(ClassroomDTO cdto) {
        this.cdto = cdto;
    }

    public TeacherDTO getSdto() {
        return tdto;
    }

    public void setSdto(TeacherDTO tdto) {
        this.tdto = tdto;
    }


    @FXML
    public  void link_add(){
            link_list.getItems().add(link_text.getText());
    }

    @FXML
    public void accordionClick(){
        link_list.getItems().clear();
    }

    public  void init() throws IOException, InterruptedException {

        cdto = rqm.fetch_classroom(cdto.getId());
        title.setText(cdto.getCoursename());
        name.setText(tdto.getName());
        id.setText(tdto.getTeachid().toString());
        VBox vb = new VBox();
        vb.setSpacing(15);
        //vb.setPadding(new Insets(2,0,2,0));
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

            tc.getDate().setText(lc.format(DateTimeFormatter.ofPattern("d MMM uuuu , HH:mm:ss ")));
            tc.getPoster().setText(p.getPosted_by());
            tc.getPost().setText(p.getText());
            tc.getMainPane().setMinHeight(75 + tc.getPost().getBoundsInLocal().getHeight());
            vb.getChildren().add(e);
            VBox.setVgrow(e, Priority.ALWAYS);
        }
        scroll.setContent(vb);
    }


    @FXML
    public void init_assignments() throws IOException, InterruptedException {


        cdto = rqm.fetch_classroom(cdto.getId());

        VBox vb = new VBox();
        vb.setSpacing(15);

        List<Node> vbnodes = new ArrayList<>();
        for(Long x : cdto.getAssignmentsHereID()){
            FXMLLoader fxl = new FXMLLoader(HelloApplication.class.getResource("assignment_tile.fxml"));
            Node e = fxl.load();
            assignment_tile_control tc = fxl.getController();
            AssignmentDTO adto = rqm.fetch_ass(x);
            tc.setTeach(true);
            tc.setTdto(tdto);
            tc.setAdto(adto);
            tc.getTeacher().setText(adto.getTitle());
            tc.getDate().setText(adto.getDeadline().toString());
            tc.getAssignment_title().setText(adto.getTitle());
            tc.init();

            e.setUserData(adto);
            vbnodes.add(e);
            VBox.setVgrow(e, Priority.ALWAYS);
        }

        Collections.sort(vbnodes , (a , b) ->{
            AssignmentDTO x = (AssignmentDTO) a.getUserData();
            AssignmentDTO y = (AssignmentDTO) b.getUserData();

            return x.getDeadline().compareTo(y.getDeadline());
        });
        vb.getChildren().addAll(vbnodes);
        scroll.setContent(vb);

    }


    @FXML
    public void create_post() throws IOException, InterruptedException {
        Date date = new Date();

        Timestamp ts = new Timestamp(date.getTime());

        if(tdto == null) return;

         ArrayList<String> linkgula = new ArrayList<>();

         linkgula.addAll(link_list.getItems());
        PostDTO pdto = new PostDTO(Long.parseLong("0") ,txtArea.getText() , ts ,tdto.getName() , linkgula , cdto.getId());

        rqm.create_post(pdto);
        cdto = rqm.fetch_classroom(cdto.getId());
        init();
    }



    @FXML
    public void createAssignment(ActionEvent event) throws IOException, InterruptedException {
        //Node root = (Node) event.getSource();
        Stage myStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CreateAssignment.fxml"));
        Scene subtractionScene = new Scene(fxmlLoader.load());
        CreateAssignment obj = fxmlLoader.getController();

        obj.setCdto(cdto);
        obj.setTdto(tdto);

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

    @FXML
    public void GoBack(ActionEvent event) throws IOException, InterruptedException {
        Node root = (Node) event.getSource();
        Stage myStage = (Stage) root.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TeacherScene2.fxml"));
        Scene subtractionScene = new Scene(fxmlLoader.load());
        TeacherScene2 obj = fxmlLoader.getController();

        myStage.setScene(subtractionScene);
        myStage.show();
    }

    @FXML
    public void GoToAttendance(ActionEvent event) throws IOException, InterruptedException {

        Stage myStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Attendance.fxml"));
        Scene subtractionScene = new Scene(fxmlLoader.load());
        Attendance obj = fxmlLoader.getController();

        obj.setCdto(cdto);
        obj.init();

        myStage.setScene(subtractionScene);
        myStage.show();
    }


}
