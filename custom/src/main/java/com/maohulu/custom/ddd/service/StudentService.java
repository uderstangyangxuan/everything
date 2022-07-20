package com.maohulu.custom.ddd.service;

import com.maohulu.custom.ddd.domain.Student;

/**
 * @author huliu
 */
public interface StudentService {

    Student queryByStudentId(Integer studentId);

    void updateStudent(Student student);

    void deleteStudentById(Integer studentId);
}
