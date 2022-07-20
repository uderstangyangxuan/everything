package com.maohulu.custom.ddd.service.impl;

import com.maohulu.custom.ddd.domain.Student;
import com.maohulu.custom.ddd.service.StudentService;

/**
 * @author huliu
 * @description TODO
 * @since 2022/4/14 10:43
 */
public class StudentServiceImpl implements StudentService {
    @Override
    public Student queryByStudentId(Integer studentId) {
        // 业务逻辑

        // mapper 入库
        return null;
    }

    @Override
    public void updateStudent(Student student) {
        // 业务逻辑

        // mapper 入库
    }

    @Override
    public void deleteStudentById(Integer studentId) {
        // 业务逻辑

        // mapper 入库
    }
}
