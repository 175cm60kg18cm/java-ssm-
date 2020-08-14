package com.mz.dao;

import com.mz.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public User findUserByName(String username);
    public int insertUser(User user);
    public List<User> findUserList(Map<String,Object> queryMap);
    public int getTotal(Map<String,Object> queryMap);
    public int edit(User user);
    public int delete(long id);
}
