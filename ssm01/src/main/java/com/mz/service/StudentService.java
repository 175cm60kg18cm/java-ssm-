package com.mz.service;

import com.mz.domain.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    public Student findStudentByName(String name);
    public int insertStudent(Student student);
    public List<Student> findStudentList(Map<String,Object> queryMap);
    public int getTotal(Map<String,Object> queryMap);
    public int edit(Student student);
    public int delete(long id);
    public List<Student> findAll();
}
