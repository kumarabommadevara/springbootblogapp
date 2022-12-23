package com.harsha.springbootblogapp.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    @Enumerated(EnumType.STRING)
    private ERole roleName;

    public ERole getRoleName() {
        return roleName;
    }


}
