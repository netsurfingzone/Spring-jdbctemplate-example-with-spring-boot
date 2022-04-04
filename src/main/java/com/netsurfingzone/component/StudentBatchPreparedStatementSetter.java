package com.netsurfingzone.component;

import com.netsurfingzone.entity.Student;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StudentBatchPreparedStatementSetter implements BatchPreparedStatementSetter {
    private List<Student> students;

    public StudentBatchPreparedStatementSetter(List<Student> students) {
        super();
        this.students = students;
    }


    @Override
    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
        try {
            Student student = students.get(i);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getRollNumber());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getBatchSize() {
        return students.size();
    }
}
