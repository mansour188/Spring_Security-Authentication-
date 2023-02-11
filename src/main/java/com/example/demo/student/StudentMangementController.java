package com.example.demo.student;


import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/manegement/api/v1/student")
public class StudentMangementController {
    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1,"mansour"),
            new Student(2,"salah"),
            new Student(3,"hasna")
    );

    @GetMapping("/get")
    public List<Student> getStudents(){
        return STUDENTS;
    }
    @PostMapping("/post")
    public Student registerStudent(@RequestBody Student student){
        return student;
    }
    @DeleteMapping("/delete/{studentId}")
    public Integer delateStudent(@PathVariable("studentId") Integer studentId ){
       return studentId;
    }
    @PutMapping("/update/{studentId}")
    public Integer updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student){
        return   studentId;
    }



}
