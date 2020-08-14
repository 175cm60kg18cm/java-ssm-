package com.mz.service;

import com.mz.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public User findUserByName(String name);
    public int insertUser(User user);
    public List<User> findUserList(Map<String,Object> queryMap);
    public int getTotal(Map<String,Object> queryMap);
    public int edit(User user);
    public int delete(long id);
}
