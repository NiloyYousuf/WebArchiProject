package com.example.projectfrontend2_2;

import com.example.projectfrontend2_2.Classroom.FileDTO;
import com.example.projectfrontend2_2.http.RequestMaker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;


public class FileView {

    private FileDTO fdto;

    @FXML
    private Text filename;

    @FXML
    private Text date;

    public Text getDate() {
        return date;
    }

    public void setDate(Text date) {
        this.date = date;
    }

    public Text getFilename() {
        return filename;
    }

    public void setFilename(Text filename) {
        this.filename = filename;
    }

    @FXML
    public void openLink() throws IOException {
        Desktop.getDesktop().browse(URI.create("http://"+ RequestMaker.host_addr +":8080/file/files/" + fdto.getId()));
    }

    public FileDTO getFdto() {
        return fdto;
    }

    public void setFdto(FileDTO fdto) {
        this.fdto = fdto;
    }
}
