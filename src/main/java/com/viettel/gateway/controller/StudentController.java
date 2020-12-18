package com.viettel.gateway.controller;

import com.viettel.gateway.domain.Student;
import com.viettel.gateway.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAll() {
        List<Student> lst = studentRepository.findAllBy();
        return new ResponseEntity<List<Student>>(lst, HttpStatus.OK);
    }
}
