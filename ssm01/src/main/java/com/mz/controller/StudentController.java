package com.mz.controller;

import com.mz.domain.Student;
import com.mz.domain.User;
import com.mz.page.Page;
import com.mz.service.ClazzService;
import com.mz.service.GradeService;
import com.mz.service.StudentService;
import com.mz.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController{
    @Autowired
    StudentService studentService;
    @Autowired
    ClazzService clazzService;;
    @Autowired
    GradeService gradeService;
    @RequestMapping("/list")
    public ModelAndView userList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student/studentList");
        modelAndView.addObject("clazzList",clazzService.findAll());
        return modelAndView;

    }
    @RequestMapping(value = "/uploadPhoto" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> uploadPhoto(@RequestParam MultipartFile photo, HttpServletRequest request,
                                    HttpServletResponse response) throws IOException {
        Map<String,String> ret=new HashMap<>();
        ModelAndView modelAndView = new ModelAndView();
        if(photo==null){
            ret.put("type","fail");
            ret.put("msg","上传的文件不能为空");
            return ret;
        }
        String path=request.getServletContext().getRealPath("/")+"\\upload\\";
    //    System.out.println("上传路径：   "+path);
        String photoFormat=photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".")+1,photo.getOriginalFilename().length());
        if(!"jpg,jpeg,png,gif".contains(photoFormat.toLowerCase())){
            System.out.println(photoFormat);
            ret.put("type","fail");
            ret.put("msg","上传的格式不正确");
            return ret;

        }
        File savePathFile=new File(path);
        if(!savePathFile.exists()){
            savePathFile.mkdir();
        }
        String fileName=new Date().getTime()+"."+photoFormat;
        photo.transferTo(new File(path+fileName));

        ret.put("type","success");
        ret.put("msg","上传成功");
        ret.put("src",request.getServletContext().getContextPath()+"/upload/"+fileName);
        return ret;
    }
    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> addUser(Student student){
        HashMap<String, String> map = new HashMap<>();
        if(student==null){
            map.put("type","fail");
            map.put("msg","用户添加错误，请联系管理员");
            return  map;
        }
        else if(student.getUsername()==null){
            map.put("type","fail");
            map.put("msg","用户名不能为空");
            return  map;
        }
        else if(student.getPassword()==null){
            map.put("type","fail");
            map.put("msg","用户密码不能为空");
            return  map;
        }
        student.setSn(StringUtils.generateSN("S","N"));
 //       System.out.println("接收到学生：  "+student);
        if(studentService.insertStudent(student)<=0){
            map.put("type","fail");
            map.put("msg","学生添加失败");
            return  map;
        }
        map.put("type","success");
        map.put("msg","用户添加成功");
        return map;
    }
    @RequestMapping(value = "/get_list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(@RequestParam(value = "username",required = false,defaultValue = "")String name ,
                                      @RequestParam(value = "clazzId") Long clazzId ,
                                      Page page )
   {

        System.out.println("收到page:  "+page+"  收到clazzId:    "+clazzId);
        Map<String,Object> ret=new HashMap<>();
        Map<String,Object> queryMap=new HashMap<>();
        queryMap.put("username","%"+name+"%");

        if(clazzId!=null)
            queryMap.put("clazzId",clazzId);
        queryMap.put("offset",page.getOffset());
        queryMap.put("pageSize",page.getRows());

       List<Student> studentList = studentService.findStudentList(queryMap);
       studentList.forEach(a-> System.out.println(a));
       ret.put("rows", studentList);
        ret.put("total",studentService.getTotal(queryMap));
        return  ret;

    }
}
//import com.mz.domain.Student;
//import com.mz.exception.AgeException;
//import com.mz.exception.MyUserException;
//import com.mz.exception.NameException;
//import com.mz.service.StudentService;
//import com.sun.scenario.effect.impl.state.LinearConvolveShadowPeer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@Controller
//@RequestMapping("/student")
//public class StudentController {
//    @Resource
//    StudentService studentService;
//    @RequestMapping("/addstudent.do")
//    public ModelAndView addStudent(Student s) throws MyUserException {
//        if(!s.getName().equals("zs")){
//            System.out.println("enter");
//
//            throw new NameException("名字不正确");
//        }
//        if(s.getAge()>=80){
//            throw new AgeException("年龄太大了");
//        }
//      //  int num= studentService.insertStudent(s);
//        int num=1;
//        String tips="注册失败";
//        if(num>0){
//            tips="注册成功";
//        }
//        ModelAndView modelAndView=new ModelAndView();
//        modelAndView.addObject("msg",tips);
//        //转发，forward:/资源具体路径，，，相当于视图解析器不存在，根目录相当于是webapp,地址栏不变
//        modelAndView.setViewName("forward:/WEB-INF/jsp/newresult.jsp");
//        return  modelAndView;
//    }
//    @RequestMapping("/liststudent.do")
//    @ResponseBody
//    public List<Student> studentList(){
//        return studentService.selectStudent();
//    }
//    /**
//     * 不能和视图解析器一块工作，相当于没有视图解析器
//     * 重定向会让浏览器重新发送一次请求,如果新请求直接请求/WEB-INF下的视图，会报404。WEB-INF洗的视图不能直接在浏览器中访问
//     * mv.addObject()会使model中的参数转化为String类型，作为重新发送请求的参数，目的是让两次 请求之间传数据
//     * 新的请求http://localhost:8080/ssm01/newresult.jsp?msg=马某注册成功
//     *由于是两次不同的请求，因此无法在新的请求request域中得到相应的参数
//     * */
//    @RequestMapping("/redirect.do")
//    public ModelAndView doDirect(Student student){
//        ModelAndView mv=new ModelAndView();
//        //参数被放到了request作用域
//        mv.addObject("msg",student.getName()+"注册成功");
//        mv.setViewName("redirect:/newresult.jsp");
//        return mv;
//    }
//}
