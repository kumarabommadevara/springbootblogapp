package com.harsha.springbootblogapp.service.impl;

import com.harsha.springbootblogapp.entity.Post;
import com.harsha.springbootblogapp.exception.ResourceNotFoundException;
import com.harsha.springbootblogapp.payloads.PostDto;
import com.harsha.springbootblogapp.repository.PostRepository;
import com.harsha.springbootblogapp.service.PostService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = modelMapper.map(postDto, Post.class);
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);

    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        if (postRepository.findById(postId).isPresent()) {
            Post oldPost = postRepository.findById(postId).get();

            oldPost.setPostName(postDto.getPostName());
            oldPost.setPostDescription(postDto.getPostDescription());

            Post savedPost = postRepository.save(oldPost);
            return modelMapper.map(savedPost, PostDto.class);
        } else {
            throw new ResourceNotFoundException("Post Id not FOund");
        }
    }

    @Override
    public List<PostDto> findAllPosts() {
        List<Post> postList = postRepository.findAll();
        return modelMapper.map(postList, new TypeToken<List<PostDto>>() {
        }.getType());
    }


    @Override
    public PostDto findPostById(Integer postId) {
        Optional<Post> post = Optional.ofNullable(postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post Id not found")));
        return modelMapper.map(post.get(), PostDto.class);

    }

    @Override
    public void deletePost(Integer postId) {
        if (postRepository.findById(postId).isPresent()) {
            Post post = postRepository.findById(postId).get();
            postRepository.delete(post);

        } else {
            throw new ResourceNotFoundException("Post Id not FOund");
        }
    }

}

