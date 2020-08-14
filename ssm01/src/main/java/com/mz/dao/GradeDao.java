package com.mz.dao;

import com.mz.domain.Grade;

import java.util.List;
import java.util.Map;

public interface GradeDao {
    public Grade findGradeByName(String name);
    public int insertGrade(Grade grade);
    public List<Grade> findGradeList(Map<String,Object> queryMap);
    public int getTotal(Map<String,Object> queryMap);
    public int edit(Grade grade);
    public int delete(long id);
    public List<Grade> findAll();
}
