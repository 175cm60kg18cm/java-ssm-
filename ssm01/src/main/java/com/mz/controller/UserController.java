package com.mz.controller;

import com.mz.domain.User;
import com.mz.page.Page;
import com.mz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @RequestMapping("/list")
    public ModelAndView userList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/userList");
        return modelAndView;

    }
    /**
     * 添加用户
     */

    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> addUser(User user){
        HashMap<String, String> map = new HashMap<>();
        if(user==null){
            map.put("type","fail");
            map.put("msg","用户添加错误，请联系管理员");
            return  map;
        }
        else if(user.getUsername()==null){
            map.put("type","fail");
            map.put("msg","用户名不能为空");
            return  map;
        }
        else if(user.getPassword()==null){
            map.put("type","fail");
            map.put("msg","用户密码不能为空");
            return  map;
        }
        userService.insertUser(user);
        map.put("type","success");
        map.put("msg","用户添加成功");
        return map;
    }
    /**
     * 修改用户
     */

    @RequestMapping(value = "/edit" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> editUser(User user){
        HashMap<String, String> map = new HashMap<>();
        if(user==null){
            map.put("type","fail");
            map.put("msg","用户添加错误，请联系管理员");
            return  map;
        }
        else if(user.getUsername()==null){
            map.put("type","fail");
            map.put("msg","用户名不能为空");
            return  map;
        }
        else if(user.getPassword()==null){
            map.put("type","fail");
            map.put("msg","用户密码不能为空");
            return  map;
        }
        User userByName = userService.findUserByName(user.getUsername());
        if(userByName!=null){
            if(user.getId()!=userByName.getId()){
                map.put("type","fail");
                map.put("msg","该用户名已存在");
                return map;
            }
        }
        if(userService.edit(user)<=0){
            map.put("type","fail");
            map.put("msg","修改用户失败");
            return map;
        }
        map.put("type","success");
        map.put("msg","用户修改成功");
        return map;
    }
    @RequestMapping(value = "/get_list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(@RequestParam(value = "username",required = false,defaultValue = "")String username ,
                                      Page page
                                      ){

 //       System.out.println("收到page:  "+page);
        Map<String,Object> ret=new HashMap<>();
        Map<String,Object> queryMap=new HashMap<>();
        queryMap.put("username","%"+username+"%");
        queryMap.put("offset",page.getOffset());
        queryMap.put("pageSize",page.getRows());
        ret.put("rows",userService.findUserList(queryMap));
        ret.put("total",userService.getTotal(queryMap));
        return  ret;
    }
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> delete(@RequestParam(value = "ids[]",required = true) Long[] ids){
        Map<String,String> map=new HashMap<>();
        Map<String,Long>qurey=new HashMap<>();

        for(Long id:ids){
            if(userService.delete(id)<=0){
                map.put("type","fail");
                map.put("msg","删除失败");
                return  map;
            }
        }
        map.put("type","success");
        map.put("msg","删除用户成功");
        return map;
    }

}
