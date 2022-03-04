package com.darkduck.Staff.Controllers;

import com.darkduck.Staff.Entities.Employee;
import com.darkduck.Staff.Entities.EmployeeForm;
import com.darkduck.Staff.Entities.Post;
import com.darkduck.Staff.Entities.PostForm;
import com.darkduck.Staff.Services.EmployeeService;
import com.darkduck.Staff.Services.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PostService postService;

    @GetMapping("/list")
    public String listUsers(Model model) {

        List<Employee> emps = employeeService.getEmployeers();
        model.addAttribute("employees", emps);
        EmployeeForm employeeForm = new EmployeeForm();
        model.addAttribute("employeeForm", employeeForm);
        List<Post> posts = postService.getPosts();
        model.addAttribute("posts", posts);
        EmployeeForm form=new EmployeeForm();
        model.addAttribute("form", form);
        return "views/index";
    }

    @GetMapping("/createEmployee")
    public String createEmployee(Model model) {
        List<Post> posts = postService.getPosts();
        model.addAttribute("posts", posts);
        PostForm postForm = new PostForm();
        model.addAttribute("postModal", postForm);
        EmployeeForm employeeForm = new EmployeeForm();
        model.addAttribute("employeeForm", employeeForm);
        model.addAttribute("isModal",false);
        Employee employee = employeeService.getEmployee(1L);
        EmployeeForm form = new EmployeeForm();
        form.setFirstName(employee.getFirstName());
        form.setSecondName(employee.getSecondName());
        form.setPostId(employee.getPost().getId());
        model.addAttribute("form", form);
        model.addAttribute("employeeId", 2L);
        return "views/createEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(Model model, @ModelAttribute("employeeForm") EmployeeForm employeeForm) {
        String firstName = employeeForm.getFirstName();
        String secondName = employeeForm.getSecondName();
        Long postId = employeeForm.getPostId();
        //logger.info(postId);
        if (firstName != null && secondName != null && postId != null) {
            Post post = postService.getPost(postId);
            employeeService.createEmployee(firstName, secondName, post);
            return "redirect:list";
        }
        return "redirect:../errorPage";
    }

    @GetMapping("/get/{id}")
    public String getEmployee(@PathVariable(value = "id") Long id, Model model) {
        Employee employee = employeeService.getEmployee(id);
        EmployeeForm form = new EmployeeForm();
        form.setFirstName(employee.getFirstName());
        form.setSecondName(employee.getSecondName());
        form.setPostId(employee.getPost().getId());
        model.addAttribute("form", form);
        model.addAttribute("employeeId", id);

        return "redirect:../list";
    }

    @PostMapping("/edit/{id}")
    public String editEmployee(Model model, @PathVariable(value = "id") Long employeeId, @ModelAttribute("employeeForm") EmployeeForm employeeForm) {
        String firstName = employeeForm.getFirstName();
        String secondName = employeeForm.getSecondName();
        Long postId = employeeForm.getPostId();
        if (firstName != null && secondName != null && postId != null) {
            Post post = postService.getPost(postId);
            Employee employee = employeeService.getEmployee(employeeId);
            employee.setFirstName(firstName);
            employee.setSecondName(secondName);
            employee.setPost(post);
            employeeService.updateEmployee(employee);
            return "redirect:list";
        }
        return "redirect:../errorPage";
    }
}
