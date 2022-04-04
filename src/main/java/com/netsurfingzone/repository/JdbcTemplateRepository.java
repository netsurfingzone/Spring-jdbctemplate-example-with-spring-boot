package com.netsurfingzone.repository;


import com.netsurfingzone.component.StudentBatchPreparedStatementSetter;
import com.netsurfingzone.component.StudentRowMapper;
import com.netsurfingzone.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcTemplateRepository implements StudentRepository {

    @Autowired
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    private JdbcTemplate secondaryJdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;



    @Override
    public int save(Student student) {
        int value = secondaryJdbcTemplate.update("insert into student (name, roll_number) values(?,?)",
                student.getName(), student.getRollNumber());
        return value;

    }

    @Override
    public int update(Student student) {
        int value = primaryJdbcTemplate.update("update student set name=?, roll_number=? where id=?",
                student.getName(), student.getRollNumber(), student.getId());
        return value;

    }

    @Override
    public Student find(Long id) {
        Student student = primaryJdbcTemplate.queryForObject("select * from student where id=?",
                BeanPropertyRowMapper.newInstance(Student.class), id);
        return student;
    }

    @Override
    public int delete(Long id) {
        int value = primaryJdbcTemplate.update("delete from student where id=?", id);
        return value;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertStudents(List<Student> students) {
        String sql = "insert into student (name, roll_number) values(?,?)";
        primaryJdbcTemplate.batchUpdate(sql, new StudentBatchPreparedStatementSetter(students));
    }


    @Transactional
    public Student retrievSingleRecord(Long id) {
        String sql = "select * from student where id = ?";
        return (Student) primaryJdbcTemplate.queryForObject(sql, new Object[]{id}, new StudentRowMapper());
    }

    @Transactional
    public List<Student> retrieveMultipleRecords() {
        String sql = "select * from student";
        List<Student> students =  primaryJdbcTemplate.query(sql, new StudentRowMapper());
        return students;
    }

    @Transactional
    public List<String> retrieveSingleColumn(){
        String sql = "select name from student";
        List<String> names =  primaryJdbcTemplate.queryForList(sql,  String.class);
        return names;
    }

    @Transactional
    public String retrieveSingleString(Long id){
        String sql = "select name from student where id = ?";
        String name =  primaryJdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
        return name;
    }

    @Transactional
    public List<Student> retrieveRowJdbcTemplateNamedParameter(String name, String rollNumber){

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", name);
        parameters.addValue("rollNumber", rollNumber);
        String query = "select * from student where name = :name and roll_Number = :rollNumber";
        List<Student> students=  (List<Student>) namedParameterJdbcTemplate.query(query, parameters, new StudentRowMapper());
        return students;
    }
}
