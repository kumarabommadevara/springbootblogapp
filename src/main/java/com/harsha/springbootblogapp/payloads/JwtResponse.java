package com.harsha.springbootblogapp.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponse {


    private String jwt;
    private int userId;
    private String userName;
    private String email;
    private String about;
    private List<String> roles;

}
