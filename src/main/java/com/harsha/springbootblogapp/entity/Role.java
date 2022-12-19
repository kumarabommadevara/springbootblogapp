package com.harsha.springbootblogapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer roleId;
    @Enumerated(EnumType.STRING)
    private ERole roleName;


}
