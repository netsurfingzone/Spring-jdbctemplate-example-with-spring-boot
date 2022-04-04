package com.netsurfingzone.serviceimpl;

import com.netsurfingzone.entity.Student;
import com.netsurfingzone.repository.StudentRepository;
import com.netsurfingzone.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public int save(Student student) {
        int value = studentRepository.save(student);
        return value;
    }

    @Transactional
    public int update(Student student) {
        int value  = studentRepository.update(student);
		return value;
    }

    @Transactional
    public Student find(Long id) {
        Student student = studentRepository.find(id);
        return student;
    }

    @Transactional
    public int delete(Long id) {
		int value = studentRepository.delete(id);
        return value;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertStudents(List<Student> students) {
       studentRepository.insertStudents(students);
    }

    @Transactional
    public Student retrievSingleRecord(Long id){
        Student student = studentRepository.retrievSingleRecord(id);
        return student;
    }

    @Transactional
    public List<Student> retrieveMultipleRecords(){
        List<Student> students = studentRepository.retrieveMultipleRecords();
        return students;
    }

    public List<String> retrieveSingleColumn(){
        List<String> names = studentRepository.retrieveSingleColumn();
        return names;
    }

    @Override
    public String retrieveSingleString(Long id) {
        String name = studentRepository.retrieveSingleString(id);
        return name;
    }

    @Override
    public List<Student> retrieveRowJdbcTemplateNamedParameter(String name, String rollNumber) {
        List<Student> students = studentRepository.retrieveRowJdbcTemplateNamedParameter(name, rollNumber);
        return students;
    }
}
