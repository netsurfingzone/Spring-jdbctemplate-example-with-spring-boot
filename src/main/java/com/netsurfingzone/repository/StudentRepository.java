package com.netsurfingzone.repository;

import com.netsurfingzone.entity.Student;

import java.util.List;


public interface StudentRepository {
    public int save(Student student);

    public int update(Student student);

    public Student find(Long id);

    public int delete(Long id);

    public void insertStudents(List<Student> students);

    public Student retrievSingleRecord(Long id);

    public List<Student> retrieveMultipleRecords();

    public List<String> retrieveSingleColumn();

    public String retrieveSingleString(Long id);

    public List<Student> retrieveRowJdbcTemplateNamedParameter(String name, String rollNumber);
}
