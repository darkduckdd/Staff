package com.darkduck.Staff.Services;

import com.darkduck.Staff.Entities.Employee;
import com.darkduck.Staff.Entities.EmployeeForm;
import com.darkduck.Staff.Entities.Post;
import com.darkduck.Staff.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void createEmployee(String firstName, String lastName, Post post) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setSecondName(lastName);
        employee.setPost(post);
        employeeRepository.save(employee);
    }

    public List<Employee> getEmployeers() {
        return employeeRepository.findAll();
    }

    public Long getCount() {
        return employeeRepository.count();
    }

    public Employee getEmployee(Long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if (optional.isPresent()) {
            employee = optional.get();
        } else {
            throw new RuntimeException("Employee not found for id :" + id);
        }
        return employee;
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
