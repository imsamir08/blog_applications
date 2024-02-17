package com.samir.blog.controllers;

import com.samir.blog.config.AppConstants;
import com.samir.blog.payloads.ApiResponse;
import com.samir.blog.payloads.FileResponse;
import com.samir.blog.payloads.PostDto;
import com.samir.blog.payloads.PostResponse;
import com.samir.blog.services.FileService;
import com.samir.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;




//    createPost
    @PostMapping("/users/{userId}/categories/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
        PostDto createdPostDto=postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(createdPostDto, HttpStatus.CREATED);
    }

    @GetMapping("users/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
        List<PostDto> postDtos=postService.getPostsByUser(userId);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }
    @GetMapping("categories/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
        List<PostDto> postDtos=postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postDto=postService.getPostById(postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false)
                                                        Integer pageNumber, @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                   @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                   @RequestParam(value = "sortDirection", defaultValue = AppConstants.SORT_DIRECTION, required = false) String sortDirection){
        PostResponse postResponse=postService.getAllPost(pageNumber, pageSize, sortBy, sortDirection);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return new ApiResponse("Post is successfully deleted", true);
    }

    @PutMapping("/posts/{postId}")
    public  ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto updatedPostDto=postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPostDto, HttpStatus.OK);
    }

//    Searching
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable(value = "keywords") String keywords){
        List<PostDto> res=postService.searchPost(keywords);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


//............uploading file or image
    @Value("${project.image}")
    private String path;
    @PostMapping("/posts/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadFile(@RequestParam("image")MultipartFile image, @PathVariable Integer postId) throws IOException {
        String fileName=fileService.uploadImage(path, image);
        PostDto postDto=postService.getPostById(postId);
        postDto.setImageName(fileName);
        PostDto updatedPostDto=postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPostDto, HttpStatus.OK);
    }
}
