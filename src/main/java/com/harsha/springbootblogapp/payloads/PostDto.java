package com.harsha.springbootblogapp.payloads;

import lombok.Data;

@Data
public class PostDto {

    private Integer postId;
    private String postName;
    private String postDescription;
}
