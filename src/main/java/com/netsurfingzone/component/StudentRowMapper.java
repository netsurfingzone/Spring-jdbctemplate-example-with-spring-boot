package com.netsurfingzone.component;

import com.netsurfingzone.entity.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper {
    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student =  new Student();
        student.setId(resultSet.getLong("id"));
        student.setName(resultSet.getString("name"));
        student.setRollNumber(resultSet.getString("roll_number"));
        return student;
    }
}
