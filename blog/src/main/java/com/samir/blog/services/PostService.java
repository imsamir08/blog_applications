package com.samir.blog.services;

import com.samir.blog.payloads.PostDto;
import com.samir.blog.payloads.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
//    create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
//    update
    PostDto updatePost(PostDto postDto, Integer postId);
//    delete
    void deletePost(Integer postId);
//    get
    PostDto getPostById(Integer postId);
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
// get ALl posts By Category
    List<PostDto> getPostsByCategory(Integer categoryId);
//    get All posts By users
    List<PostDto> getPostsByUser(Integer userId);
    List<PostDto> searchPost(String keyword);
}
