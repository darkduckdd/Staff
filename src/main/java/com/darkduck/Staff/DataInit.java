package com.darkduck.Staff;

import com.darkduck.Staff.Entities.Post;
import com.darkduck.Staff.Repositories.EmployeeRepository;
import com.darkduck.Staff.Repositories.PostRepository;
import com.darkduck.Staff.Services.EmployeeService;
import com.darkduck.Staff.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements ApplicationRunner {
    private EmployeeService employeeService;
    private PostService postService;

    @Autowired
    public DataInit(EmployeeService employeeService, PostService postService) {
        this.employeeService = employeeService;
        this.postService = postService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long countEmp = employeeService.getCount();
        long countPost = postService.getCount();
        if (countPost == 0) {
            postService.createPost("IT");
            postService.createPost("Admin");
        }
        if (countEmp == 0) {
            Post post = postService.getPost("IT");
            employeeService.createEmployee("Adil", "Aibekov", post);
            Post pt = postService.getPost("Admin");
            employeeService.createEmployee("Sanjo", "Samatov", pt);
        }
    }
}
