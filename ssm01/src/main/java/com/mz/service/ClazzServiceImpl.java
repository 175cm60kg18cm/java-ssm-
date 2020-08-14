package com.mz.service;
import com.mz.dao.ClazzDao;
import com.mz.domain.Clazz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    ClazzDao clazzDao;
    @Override
    public Clazz findClazzByName(String name) {
        return clazzDao.findClazzByName(name);
    }

    @Override
    public int insertClazz(Clazz clazz) {
        return clazzDao.insertClazz(clazz);
    }

    @Override
    public List<Clazz> findClazzList(Map<String, Object> queryMap) {
        return clazzDao.findClazzList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return clazzDao.getTotal(queryMap);
    }

    @Override
    public int edit(Clazz clazz) {
        return clazzDao.edit(clazz);
    }

    @Override
    public int delete(long id) {
        return clazzDao.delete(id);
    }

    @Override
    public List<Clazz> findAll() {
        return clazzDao.findAll();
    }
}
