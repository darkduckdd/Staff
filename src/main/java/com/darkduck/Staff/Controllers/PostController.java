package com.darkduck.Staff.Controllers;

import com.darkduck.Staff.Entities.Post;
import com.darkduck.Staff.Entities.PostForm;
import com.darkduck.Staff.Repositories.PostRepository;
import com.darkduck.Staff.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/createPost")
    private String addPost(Model model, @ModelAttribute("postModal") PostForm postForm){
        String name=postForm.getName();
        if(name!=null){
            if(!postService.isPresent(name)){
                postService.createPost(name);
                return "redirect:../employee/createEmployee";
            }
        }
        return "forward:../employee/list";
    }
    /*@GetMapping("/get/{id}")
    public Optional<Post> getPost(@PathVariable("id") Long postId){
        return  postRepository.findById(postId);
    }

    @PostMapping("/add")
    public Post addPost(@RequestBody Post post){
     return postRepository.save(post);
    }*/
}
