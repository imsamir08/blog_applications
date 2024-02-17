package com.samir.blog.repositories;

import com.samir.blog.entities.Category;
import com.samir.blog.entities.Post;
import com.samir.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

//    we can also create method for own purpose
    List<Post> findByCategory(Category categoryId);
    List<Post> findByUser(User userId);

//    searching by fields
    List<Post> findByTitleContaining(String fields);
}
