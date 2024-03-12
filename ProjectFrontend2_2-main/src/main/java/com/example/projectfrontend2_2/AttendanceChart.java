package com.example.projectfrontend2_2;


import com.example.projectfrontend2_2.Classroom.ClassroomDTO;
import com.example.projectfrontend2_2.Student.StudentDTO;
import com.example.projectfrontend2_2.http.RequestMaker;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AttendanceChart {
    @FXML
    private BarChart<?, ?> barChart;
    @FXML
    private CategoryAxis x;

    @FXML
    private CategoryAxis y;


    private StudentDTO studentDTO;

    private RequestMaker rqm = new RequestMaker();


    public void init() throws IOException, InterruptedException {
        XYChart.Series series1 = new XYChart.Series<>();
        series1.setName("2003");


//        series1.getData().add(new XYChart.Data("Austria", 2323));
//        series1.getData().add(new XYChart.Data("Australia", 4423));
//        series1.getData().add(new XYChart.Data("France", 59992));
//        series1.getData().add(new XYChart.Data("Bangladesh", 23433));
//        series1.getData().add(new XYChart.Data("India", 10000));

        for(Long class_id : studentDTO.getClassroom_id()){
            ClassroomDTO cdto = rqm.fetch_classroom(class_id);

            Float total = rqm.get_all_attendance(cdto.getId() , studentDTO.getStudid());
            Float present = rqm.get_present_attendance(cdto.getId() , studentDTO.getStudid());
            Double result = (present/total) * 100.00;
            if(result.isNaN()){
                result =  0.0;
            }
            System.out.println(result);

            series1.getData().add(new XYChart.Data(cdto.getCoursename(), result));
        }





        barChart.getData().addAll(series1);
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }
}
