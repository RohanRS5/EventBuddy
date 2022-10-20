package com.example.StudentService.service;

import com.example.StudentService.entity.Student;
import com.example.StudentService.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student createNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Student already taken!");
        }
        return studentRepository.save(student);
    }

    public Student deleteStudent(String stuId) {
        boolean exists = studentRepository.existsById(stuId);
        if(!exists){
            throw new IllegalStateException(
                    "Student with ID : " + stuId + " does not exist."
            );
        }
        studentRepository.deleteById(stuId);

        return null;
    }

    public ResponseEntity<Student> updateStudent(String stuId, Student student) {
        Optional<Student> studentOptional = studentRepository.findById(stuId);
        if(studentOptional.isPresent()){

            Student _student = studentOptional.get();
            _student.setStudentName(student.getStudentName());
            _student.setEmail(student.getEmail());
            _student.setPassword(student.getPassword());
            _student.setStudentDepartment(student.getStudentDepartment());
            _student.setStudentDivision(student.getStudentDivision());
            _student.setStudentConsent(student.getStudentConsent());

            return new ResponseEntity<>(studentRepository.save(_student), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public Student getStudentByStuId(String stuId) {
        Optional<Student> studentOptional = studentRepository.findById(stuId);
        if(studentOptional.isPresent()){
            return studentRepository.findByStuId(stuId);
        }
        else {
            throw new IllegalStateException("Student with ID" +
                    stuId + " does not exist.");
        }
    }

    public String signin(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            Student _student = studentOptional.get();
            if (student.getPassword().equals(_student.getPassword())) {
                return "valid";
            }
        }
        return "invalid";
    }




}