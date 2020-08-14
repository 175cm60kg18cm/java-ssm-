package com.mz.service;

import com.mz.dao.GradeDao;
import com.mz.domain.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    GradeDao gradeDao;
    @Override
    public Grade findGradeByName(String name) {
        return gradeDao.findGradeByName(name);
    }

    @Override
    public int insertGrade(Grade grade) {
        return gradeDao.insertGrade(grade);
    }

    @Override
    public List<Grade> findGradeList(Map<String, Object> queryMap) {
        return gradeDao.findGradeList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return gradeDao.getTotal(queryMap);
    }

    @Override
    public int edit(Grade grade) {
        return gradeDao.edit(grade);
    }

    @Override
    public int delete(long id) {
        return gradeDao.delete(id);
    }

    @Override
    public List<Grade> findAll() {
        return gradeDao.findAll();
    }
}
