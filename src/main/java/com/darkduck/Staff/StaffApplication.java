package com.darkduck.Staff;

import com.darkduck.Staff.Services.EmployeeService;
import com.darkduck.Staff.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.GregorianCalendar;

@SpringBootApplication
@EnableScheduling
public class StaffApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaffApplication.class, args);
    }

    @Scheduled(fixedRate = 50000)
    public void printDate() {
        System.out.println("RC Time Service: " + new GregorianCalendar().getTime());
    }
}