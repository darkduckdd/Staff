package com.darkduck.Staff.Services;

import com.darkduck.Staff.Entities.Post;
import com.darkduck.Staff.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public void createPost(String name) {
        Post post = new Post();
        //Set<Employee> employees = new HashSet<>();
        post.setName(name);
        //post.setEmployees(employees);
        postRepository.save(post);
    }

    public boolean isPresent(String name) {
        Post post = postRepository.findByName(name);
        if (post != null) {
            return true;
        }
        return false;
    }

    public Post getPost(String name) {
        Post post = postRepository.findByName(name);
        if (post != null) {
            return post;
        }
        return null;
    }

    public Long getCount(){
        return postRepository.count();
    }
    public Post getPost(Long id){
        return postRepository.getById(id);
    }
}
