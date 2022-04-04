package com.netsurfingzone.controller;

import com.netsurfingzone.entity.Student;
import com.netsurfingzone.repository.StudentRepository;
import com.netsurfingzone.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/create")
    public String createStudent1(@RequestBody Student student) {
        int value = studentService.save(student);
        if (value == 1) {
            return "Record inserted succesfully";
        }
        return "Problem while inserting record";
    }

    @PutMapping("/update")
    public String updateStudent(@RequestBody Student student) {
        int value = studentService.update(student);
        if (value == 1) {
            return "Record updated succesfully";
        }
        return "Problem while updatig record";

    }

    @GetMapping("/{id}")
    public Student find(@PathVariable Long id) {
        Student student = studentService.find(id);
        return student;
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        int value = studentService.delete(id);
        if (value == 1) {
            return "Record deleted succesfully";
        }
        return "Problem while deleting record";
    }

    @PostMapping("/createall")
    public String createStudent1(@RequestBody List<Student> students) {
        studentService.insertStudents(students);
        return "Records inserted successfully";
    }

    @GetMapping("/retrievsinglerecord/{id}")
    public Student retrievSingleRecord(@PathVariable Long id) {
        Student student = studentService.retrievSingleRecord(id);
        return student;
    }
    @GetMapping("/retrievemultiplerecords")
    public List<Student> retrieveMultipleRecords(){
        List<Student> students = studentService.retrieveMultipleRecords();
        return students;
    }

    @GetMapping("/retrievesinglecolumns")
    public List<String> retrieveSingleCoumn(){
        List<String> names = studentService.retrieveSingleColumn();
        return names;
    }

    @GetMapping("/retrievesinglestring/{id}")
    public String retrieveSingleString(@PathVariable Long id){
        String name = studentService.retrieveSingleString(id);
        return name;
    }

    @GetMapping("/NamedParameter/{name}/{rollNumber}")
    public List<Student> retrieveRowJdbcTemplateNamedParameter(@PathVariable String name, @PathVariable String rollNumber){
        List<Student> students = studentService.retrieveRowJdbcTemplateNamedParameter(name, rollNumber);
        return students;
    }
}
