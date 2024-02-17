package com.samir.blog.services.impl;

import com.samir.blog.entities.Category;
import com.samir.blog.entities.Post;
import com.samir.blog.entities.User;
import com.samir.blog.exceptions.ResourceNotFoundException;
import com.samir.blog.payloads.PostDto;
import com.samir.blog.payloads.PostResponse;
import com.samir.blog.repositories.CategoryRepo;
import com.samir.blog.repositories.PostRepo;
import com.samir.blog.repositories.UserRepo;
import com.samir.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User Id", userId));
        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));

        Post post=modelMapper.map(postDto, Post.class);

        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post savedPost=postRepo.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }

//    PostDto postDto -> new post, Integer postId -> old post id we can get old post from this
    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
        post.setTitle(post.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost=postRepo.save(post);
        return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
        postRepo.delete(post);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
        Sort sort=null;
        if(sortDirection.equalsIgnoreCase("asc")){
            sort=Sort.by(sortBy).ascending();
        }else sort=Sort.by(sortBy).descending();

        Page<Post> pagePost = postRepo.findAll(PageRequest.of(pageNumber, pageSize, sort));
        List<Post> allPosts = pagePost.getContent();
        List<PostDto> postDtos=allPosts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
        List<Post> posts=postRepo.findByCategory(category);
        List<PostDto> postDtos=posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User Id", userId));
        List<Post> posts=postRepo.findByUser(user);
        List<PostDto> postDtos=posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        List<Post> posts=postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtos=posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
