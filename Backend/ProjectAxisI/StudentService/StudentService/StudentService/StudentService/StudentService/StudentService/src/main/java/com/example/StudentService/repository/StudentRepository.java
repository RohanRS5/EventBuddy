package com.example.StudentService.repository;

import com.example.StudentService.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

    Student findByStuId(String StuId);

    Optional<Student> findStudentByEmail(String Email);

}
