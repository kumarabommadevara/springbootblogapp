package com.harsha.springbootblogapp.service;

import com.harsha.springbootblogapp.payloads.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostDto updatePost(PostDto postDto, Integer postId);

    List<PostDto> findAllPosts();

    PostDto findPostById(Integer postId);

    void deletePost(Integer postId);

}
