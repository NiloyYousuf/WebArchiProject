package com.example.projectfrontend2_2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class postcontrol {
    public Label getPostedby() {
        return postedby;
    }

    public void setPostedby(Label postedby) {
        this.postedby = postedby;
    }

    public Label getPostedwhen() {
        return postedwhen;
    }

    public void setPostedwhen(Label postedwhen) {
        this.postedwhen = postedwhen;
    }

    public Label getPostedwhat() {
        return postedwhat;
    }

    public void setPostedwhat(Label postedwhat) {
        this.postedwhat = postedwhat;
    }

    @FXML
    private Label postedby;
    @FXML
    private Label postedwhen;
    @FXML
    private Label postedwhat;


}

