package com.example.projectfrontend2_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class Routine {

    @FXML
    private Button goBack;
    @FXML
    private Rectangle routine;
    Image image = new Image("https://www.bogotobogo.com/images/java/tutorial/java_images/Duke256.png");
    ImagePattern imagePattern = new ImagePattern(image);
    @FXML
    protected void goBackToClass(ActionEvent event) throws IOException {
        Node root = (Node) event.getSource();
        Stage myStage = (Stage) root.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ClassScene.fxml"));
        Scene x = new Scene(fxmlLoader.load(), 800, 600);
        myStage.setScene(x);
        myStage.show();
    }

    protected void initialize(){
        routine.setFill(imagePattern);
    }
}
