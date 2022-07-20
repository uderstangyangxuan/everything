package com.maohulu.custom.ddd.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author huliu
 * @description 学生类
 * @since 2022/4/14 10:43
 */
//@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Integer id;
    private Integer age;
    private String name;
    private String grade;
    private String address;
    private List<String> classes;

    public void updateStudentAge(Integer age) {
        this.age = age;
    }

    public void updateStudentName(String name) {
        this.name = name;
    }
}
