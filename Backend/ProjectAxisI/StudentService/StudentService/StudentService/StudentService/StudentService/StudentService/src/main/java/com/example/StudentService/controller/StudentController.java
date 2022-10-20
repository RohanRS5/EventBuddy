package com.example.StudentService.controller;

import com.example.StudentService.entity.Student;
import com.example.StudentService.repository.StudentRepository;
import com.example.StudentService.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student/")
@CrossOrigin(origins = "*")
public class StudentController {

    private StudentService studentService;

    private StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentService studentService) {this.studentService = studentService;}

    @PostMapping
    public Student createStudent(@RequestBody Student stu){
        return studentService.createNewStudent(stu);
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getStudents();
    }

    @GetMapping(path = "{stuId}")
    public Student getStudentByStuId(@PathVariable("stuId") String stuId) {
        return studentService.getStudentByStuId(stuId);
    }

    @PutMapping(path = "{stuId}")
    public void updateStudent(@PathVariable("stuId") String stuId, @RequestBody Student student){
        studentService.updateStudent(stuId,student);
    }



    @DeleteMapping(path = "{stuId}")
    public Student deleteStudent(@PathVariable("stuId") String stuId){
        return studentService.deleteStudent(stuId);
    }

    @PostMapping(path = "/signin")
    public String signin(@RequestBody Student student){
        System.out.println(student);
        return studentService.signin(student);
    }

}
