package com.example.classroom.post;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

    private Long id;

    private String text;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp time;

    private String posted_by;

    private List<String> link = new ArrayList<String>();

    private Long classroom_id;

    public PostDTO(Post post) {
        this.id = post.getId();
        this.text = post.getText();
        this.time = post.getTime();
        this.posted_by = post.getPosted_by();
        this.link = post.getLink();
        this.classroom_id = post.getClassroom().getId();
    }
}
