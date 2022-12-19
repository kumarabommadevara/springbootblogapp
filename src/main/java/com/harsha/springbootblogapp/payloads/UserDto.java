package com.harsha.springbootblogapp.payloads;

import com.harsha.springbootblogapp.entity.Role;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A DTO for the {@link com.harsha.springbootblogapp.entity.User} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto implements Serializable {
    private  Integer id;
    @NotNull(message = "name cannot be empty")
    @Size(min = 4,max = 10)
    private  String name;
    @NotNull(message = "Email Cannot be Empty")
    @Email
    private  String email;
    private  String password;
    private  String about;
    private List<Role> roles;

    public UserDto(String userName, String email, String password) {
        this.name=userName;
        this.email=email;
        this.password=password;
    }

    public void setRole(List<Role> roles) {
        this.roles=roles;
    }
}