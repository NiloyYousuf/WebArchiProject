package com.example.projectfrontend2_2.http;

import com.example.projectfrontend2_2.Classroom.*;
import com.example.projectfrontend2_2.Login.LoginDTO;
import com.example.projectfrontend2_2.MultipartUtility;
import com.example.projectfrontend2_2.Student.StudentDTO;
import com.example.projectfrontend2_2.courseReg.CourseRegDTO;
import com.example.projectfrontend2_2.post.PostDTO;
import com.example.projectfrontend2_2.teacher.TeacherDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.*;
import java.net.*;
import java.util.ArrayList;


public class RequestMaker {

    private String url = "http://localhost:8080";
    private Gson gson;

    public static String host_addr = "localhost";

    public RequestMaker() {
        gson = new Gson();
        url = "http://"+host_addr+":8080";
    }

    public StudentDTO login_attempt(LoginDTO ldto , String path) throws IOException, InterruptedException {

        gson = new Gson();

        System.out.println(gson.toJson(ldto));
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(url + path)).
                POST(HttpRequest.BodyPublishers.ofString(gson.toJson(ldto))).
                header("Content-Type" , "application/json").
                build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body().toString());
            StudentDTO sdto = gson.fromJson(response.body().toString() , StudentDTO.class);



        return sdto;
    }

    public TeacherDTO login_attempt_teacher(LoginDTO ldto , String path) throws IOException, InterruptedException {

        gson = new Gson();

        System.out.println(gson.toJson(ldto));
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(url + path)).
                POST(HttpRequest.BodyPublishers.ofString(gson.toJson(ldto))).
                header("Content-Type" , "application/json").
                build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body().toString());
        TeacherDTO sdto = gson.fromJson(response.body().toString() , TeacherDTO.class);



        return sdto;
    }

    public Boolean course_register_attempt(CourseRegDTO crdto) throws IOException, InterruptedException {

        gson = new Gson();

        System.out.println(gson.toJson(crdto));
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(url + "/fetch/classroom/register")).
                POST(HttpRequest.BodyPublishers.ofString(gson.toJson(crdto))).
                header("Content-Type" , "application/json").
                build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String check = response.toString();

        if(check.equals("Paisi")){
            return true;
        }
        return false;
    }

    public void create_post(PostDTO pdto) throws IOException, InterruptedException {

        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        gson = builder.create();


        System.out.println(gson.toJson(pdto));
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(url + "/classroom/createpost")).
                POST(HttpRequest.BodyPublishers.ofString(gson.toJson(pdto))).
                header("Content-Type" , "application/json").
                build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


    }

    public void create_attendance(Attendance a) throws IOException, InterruptedException {

        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd");
        gson = builder.create();


        System.out.println(gson.toJson(a));
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(url + "/classroom/createattendance")).
                POST(HttpRequest.BodyPublishers.ofString(gson.toJson(a))).
                header("Content-Type" , "application/json").
                build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


    }
    public ClassroomDTO fetch_classroom(Long Class_id) throws IOException, InterruptedException {

        gson = new Gson();


        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(url + "/fetch/classroom/" + Class_id.toString())).
                GET().
                build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body().toString());
        ClassroomDTO cdto = gson.fromJson(response.body().toString() , ClassroomDTO.class);


        System.out.println("here" + cdto.getDept());
        return cdto;
    }

    public Float get_all_attendance(Long Class_id , Long studid) throws IOException, InterruptedException {

        gson = new Gson();


        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(url + "/classroom/stat/allclasses/" + Class_id.toString() + "/" + studid.toString())).
                GET().
                build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body().toString());
        Float cdto = gson.fromJson(response.body().toString() , Float.class);



        return cdto;
    }

    public Float get_present_attendance(Long Class_id , Long studid) throws IOException, InterruptedException {

        gson = new Gson();


        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(url + "/classroom/stat/presentclasses/" + Class_id.toString() + "/" + studid.toString())).
                GET().
                build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body().toString());
        Float cdto = gson.fromJson(response.body().toString() , Float.class);



        return cdto;
    }

    public StudentDTO fetch_student(Long id) throws IOException, InterruptedException {

        gson = new Gson();


        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(url + "/fetch/student/" + id)).
                GET().
                build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body().toString());
        StudentDTO cdto = gson.fromJson(response.body().toString() , StudentDTO.class);


        System.out.println("here" + cdto.getDept());
        return cdto;
    }

    public List<ClassroomDTO> fetch_all_classroom() throws IOException, InterruptedException {

        gson = new Gson();


        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(url + "/fetch/classroom/all" )).
                GET().
                build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Type listtype = new TypeToken<List<ClassroomDTO>>(){}.getType();
        System.out.println(response.body().toString());
        List<ClassroomDTO> cdto = gson.fromJson(response.body().toString() , listtype);


        System.out.println("here" );
        return cdto;
    }

    public PostDTO fetch_post(Long id) throws IOException, InterruptedException {


        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        gson = builder.create();




        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(url + "/classroom/getpost/"+id )).
                GET().
                build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Type listtype = new TypeToken<List<ClassroomDTO>>(){}.getType();
        System.out.println(response.body().toString());
        PostDTO pdto = gson.fromJson(response.body().toString() , PostDTO.class);


        System.out.println("here" );
        return pdto;
    }
    public AssignmentDTO fetch_ass(Long id) throws IOException, InterruptedException {


        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        gson = builder.create();




        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(url + "/classroom/getassignment/"+id )).
                GET().
                build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body().toString());
        AssignmentDTO pdto = gson.fromJson(response.body().toString() , AssignmentDTO.class);



        return pdto;
    }

    public FileDTO fetch_file_info(Long id) throws IOException, InterruptedException {


        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        gson = builder.create();




        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(url + "/file/fileinfo/"+id )).
                GET().
                build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body().toString());
        FileDTO pdto = gson.fromJson(response.body().toString() , FileDTO.class);


        System.out.println("here" );
        return pdto;
    }

    public SubmissionDTO fetch_submission_info(String id) throws IOException, InterruptedException {


        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        gson = builder.create();




        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(url + "/classroom/getsubmission/"+id )).
                GET().
                build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body().toString());
        SubmissionDTO pdto = gson.fromJson(response.body().toString() , SubmissionDTO.class);


        System.out.println("here" );
        return pdto;
    }

    public void create_assignment(AssignmentDTO pdto) throws IOException, InterruptedException {

        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        gson = builder.create();


        System.out.println(gson.toJson(pdto));
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(url + "/classroom/createassignment")).
                POST(HttpRequest.BodyPublishers.ofString(gson.toJson(pdto))).
                header("Content-Type" , "application/json").
                build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


    }

    public void create_submit(SubmissionDTO pdto) throws IOException, InterruptedException {

        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        gson = builder.create();


        System.out.println(gson.toJson(pdto));
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(url + "/classroom/createsubmission")).
                POST(HttpRequest.BodyPublishers.ofString(gson.toJson(pdto))).
                header("Content-Type" , "application/json").
                build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


    }

    public Long upload_file(String path) throws URISyntaxException, IOException, InterruptedException {

        String charset = "UTF-8";
        MultipartUtility multipart = new MultipartUtility(url + "/file/upload", charset);

        File uploadFile1 = new File(path);
//        multipart.addHeaderField("User-Agent", "CodeJava");
//        multipart.addHeaderField("Test-Header", "Header-Value");
//
//        multipart.addFormField("description", "Cool Pictures");
//        multipart.addFormField("keywords", "Java,upload,Spring");

        multipart.addFilePart("file", uploadFile1);


        List<String> response = multipart.finish();


        System.out.println("SERVER REPLIED:" + Long.parseLong(response.get(0)));


        return Long.parseLong(response.get(0));
    }
}
