package com.mz.service;

import com.mz.domain.Clazz;

import java.util.List;
import java.util.Map;

public interface ClazzService {
    public Clazz findClazzByName(String name);
    public int insertClazz(Clazz clazz);
    public List<Clazz> findClazzList(Map<String,Object> queryMap);
    public int getTotal(Map<String,Object> queryMap);
    public int edit(Clazz clazz);
    public int delete(long id);
    List<Clazz> findAll();
}
