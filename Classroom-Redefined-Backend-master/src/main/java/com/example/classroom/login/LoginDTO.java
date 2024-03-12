package com.example.classroom.login;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private Long common_id;
    private String password;
}
