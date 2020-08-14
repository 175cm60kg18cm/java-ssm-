package com.mz.controller;

import com.mz.domain.Student;
import com.mz.domain.User;
import com.mz.service.StudentService;
import com.mz.service.UserService;
import com.mz.util.CpachaUtil;
import com.sun.glass.ui.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
* 系统主页控制器
*
* */
@Controller
@RequestMapping("/system")
public class SystemController {
    @Autowired
    StudentService studentService;
    @Autowired
    UserService userService;
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "system/index";
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("system/login");
        return modelAndView;

    }
    @RequestMapping(value = "/login_out",method = RequestMethod.GET)
    public String login_out(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect: index";

    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> login2(@RequestParam("username")String username,
                                     @RequestParam("password")String password,
                                     @RequestParam("vcode")String vcode,
                                     @RequestParam("type")int type,
                                     HttpServletRequest request){
        HashMap<String, String> hashMap = new HashMap<>();
        if(StringUtils.isEmpty(username)) {
            hashMap.put("type","fail");
            hashMap.put("msg","用户不能为空");
        }
        else if(StringUtils.isEmpty(password)) {
            hashMap.put("type","fail");
            hashMap.put("msg","密码不能为空");
        }
        else if(StringUtils.isEmpty(vcode)){
            hashMap.put("type","fail");
            hashMap.put("msg","用户验证码不能为空");
        }
        else{
            String codeInput= (String) request.getSession().getAttribute("loginCpacha");
            if(!codeInput.toUpperCase().equals(vcode.toUpperCase())){
                hashMap.put("type","fail");
                hashMap.put("msg","用户验证码错误");
                return hashMap;
            }
            if(type==1){
                User user = userService.findUserByName(username);
                if(user==null){
                    hashMap.put("type","fail");
                    hashMap.put("msg","用户不存在");
                    return hashMap;
                }
                if(!user.getPassword().equals(password)){
                    hashMap.put("type","fail");
                    hashMap.put("msg","密码错误");
                    return hashMap;
                }
                request.getSession().setAttribute("user",user);
            }
            if(type==2){
                Student student = studentService.findStudentByName(username);
                if(student==null){
                    hashMap.put("type","fail");
                    hashMap.put("msg","用户不存在");
                    return hashMap;
                }
                if(!student.getPassword().equals(password)){
                    hashMap.put("type","fail");
                    hashMap.put("msg","密码错误");
                    return hashMap;
                }
                request.getSession().setAttribute("user",student);
            }
            hashMap.put("type","success");
            hashMap.put("msg","登录成功");
            request.getSession().setAttribute("userType",type);
        }
        return hashMap;

    }
    @RequestMapping(value = "/get_spacha",method = RequestMethod.GET)
    public void get_spacha(HttpServletRequest request, HttpServletResponse response){
        System.out.println("获取验证码");
        CpachaUtil cpachaUtil = new CpachaUtil(4,98,33);
        String vCode = cpachaUtil.generatorVCode();
        HttpSession session = request.getSession();
        BufferedImage bufferedImage = cpachaUtil.generatorRotateVCodeImage(vCode, true);
        session.setAttribute("loginCpacha", vCode);
        try {
            ImageIO.write(bufferedImage,"gif", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
