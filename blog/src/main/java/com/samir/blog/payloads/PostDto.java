package com.samir.blog.payloads;
import com.samir.blog.entities.Category;
import com.samir.blog.entities.User;
import lombok.Data;

import java.util.Date;

@Data
public class PostDto {
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;
}
