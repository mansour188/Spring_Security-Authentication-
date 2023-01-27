package com.example.demo.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")

public class studentController {
    private static final List<Student>  STUDENTS = Arrays.asList(
            new Student(1,"mansour"),
            new Student(2,"salah"),
            new Student(3,"hasna")
    );
    @GetMapping("/{studentId}")
    public Student  getStudent(@PathVariable("studentId")  Integer studentId){
        return STUDENTS
                .stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(()->new IllegalStateException("student "+studentId+"does not exist"));


    }

}
