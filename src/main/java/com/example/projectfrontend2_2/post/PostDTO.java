package com.example.projectfrontend2_2.post;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PostDTO {
    private Long id;

    private String text;

    private Timestamp time;

    private String posted_by;

    private List<String> link;

    private Long classroom_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getPosted_by() {
        return posted_by;
    }

    public void setPosted_by(String posted_by) {
        this.posted_by = posted_by;
    }

    public List<String> getLink() {
        return link;
    }

    public void setLink(ArrayList<String> link) {
        this.link = link;
    }

    public Long getClassroom_id() {
        return classroom_id;
    }

    public void setClassroom_id(Long classroom_id) {
        this.classroom_id = classroom_id;
    }

    public PostDTO(Long id, String text, Timestamp time, String posted_by, ArrayList<String> link, Long classroom_id) {
        this.id = id;
        this.text = text;
        this.time = time;
        this.posted_by = posted_by;
        this.link = link;
        this.classroom_id = classroom_id;
    }
}
