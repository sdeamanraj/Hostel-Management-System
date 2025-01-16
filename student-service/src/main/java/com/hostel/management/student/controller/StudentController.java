package com.hostel.management.student.controller;

import com.hostel.management.student.entity.Student;
import com.hostel.management.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        // Save the student
        Student savedStudent = studentService.addStudent(student);

        // Notify Room Service
        String roomServiceUrl = "http://localhost:8082/rooms/assignStudent";
        restTemplate.put(roomServiceUrl + "?roomNumber=" + student.getRoomNumber(), null);

        return savedStudent;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
}
