package com.harsha.springbootblogapp.payloads;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class LoginRequest {

    private String userName;
    private String password;

    public String getuserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
