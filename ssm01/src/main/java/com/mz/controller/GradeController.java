package com.mz.controller;

import com.mz.domain.Grade;
import com.mz.page.Page;
import com.mz.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    GradeService gradeService;
    @RequestMapping("/list")
    public ModelAndView userList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("grade/gradeList");
        return modelAndView;

    }
    @RequestMapping(value = "/get_list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(@RequestParam(value = "name",required = false,defaultValue = "")String name ,
                                      Page page
    ){

        System.out.println("收到page:  "+page);
        Map<String,Object> ret=new HashMap<>();
        Map<String,Object> queryMap=new HashMap<>();
        queryMap.put("name","%"+name+"%");
        queryMap.put("offset",page.getOffset());
        queryMap.put("pageSize",page.getRows());
        ret.put("rows",gradeService.findGradeList(queryMap));
        ret.put("total",gradeService.getTotal(queryMap));
        return  ret;
    }
    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> add(Grade grade){
        Map<String,String> ret=new HashMap<>();
        if(StringUtils.isEmpty(grade.getName())){
            ret.put("type","fail");
            ret.put("msg","年级名不能为空");
            return ret;
        }
        if(gradeService.insertGrade(grade)<=0){
            ret.put("type","fail");
            ret.put("msg","年级添加失败");
            return ret;
        }
        ret.put("type","success");
        ret.put("msg","年级添加成功");
        return ret;
    }
    /*
    * 修改页面
    *
    * */
    @RequestMapping(value = "/edit" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> edit(Grade grade){
        Map<String,String> ret=new HashMap<>();
        if(StringUtils.isEmpty(grade.getName())){
            ret.put("type","fail");
            ret.put("msg","年级名不能为空");
            return ret;
        }
        if(gradeService.edit(grade)<=0){
            System.out.println("接收到年级： "+grade);
            ret.put("type","fail");
            ret.put("msg","年级修改失败");
            return ret;
        }
        ret.put("type","success");
        ret.put("msg","年级添加成功");
        return ret;
    }
    @RequestMapping(value = "/delete" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> delete(@RequestParam(value = "ids[]",required = true) Long[] ids){
        Map<String,String> ret=new HashMap<>();
        for(long id:ids){
            if(gradeService.delete(id)<=0){
                ret.put("type","fail");
                ret.put("msg","删除年级失败");
                return ret;
            }
        }
        ret.put("type","success");
        ret.put("msg","年级删除成功");
        return ret;
    }
}
