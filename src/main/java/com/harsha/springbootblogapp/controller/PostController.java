package com.harsha.springbootblogapp.controller;

import com.harsha.springbootblogapp.payloads.PostDto;
import com.harsha.springbootblogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/post")
public class PostController {
    @Autowired
    PostService postService;


    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {

        PostDto createdPost = postService.createPost(postDto);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable("postId") Integer postId) {

        PostDto updatedPost = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<PostDto>> getAllPosts() {

        List<PostDto> postServiceAllPosts = postService.findAllPosts();
        return new ResponseEntity<>(postServiceAllPosts, HttpStatus.OK);

    }

    @GetMapping("/id/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("postId") int postId) {

        PostDto postDto = postService.findPostById(postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);

    }

    @DeleteMapping("/id/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable("postId") int postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>("Deleted post Successfully", HttpStatus.OK);
    }


}
