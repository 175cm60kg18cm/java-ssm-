package com.mz.service;

import com.mz.dao.StudentDao;
import com.mz.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDao studentDao;
    @Override
    public Student findStudentByName(String name) {
        return studentDao.findStudentByName(name);
    }

    @Override
    public int insertStudent(Student student) {
        return studentDao.insertStudent(student);
    }

    @Override
    public List<Student> findStudentList(Map<String, Object> queryMap) {
        return studentDao.findStudentList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return studentDao.getTotal(queryMap);
    }

    @Override
    public int edit(Student student) {
        return studentDao.edit(student);
    }

    @Override
    public int delete(long id) {
        return 0;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }
}
