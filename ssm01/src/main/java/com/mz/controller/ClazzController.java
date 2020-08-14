package com.mz.controller;

import com.mz.domain.Clazz;
import com.mz.domain.Grade;
import com.mz.page.Page;
import com.mz.service.ClazzService;
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
@RequestMapping("/clazz")
public class ClazzController {
    @Autowired
    ClazzService clazzService;
    @Autowired
    GradeService gradeService;
    @RequestMapping("/list")
    public ModelAndView userList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("clazz/clazzList");
        modelAndView.addObject("gradeList",gradeService.findAll());

        return modelAndView;

    }
    @RequestMapping(value = "/get_list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(@RequestParam(value = "name",required = false,defaultValue = "")String name ,
                                      @RequestParam(value = "gradeId") Long gradeId ,
                                      Page page
    ){

        System.out.println("收到page:  "+page+"  收到gradeId:    "+gradeId);
        Map<String,Object> ret=new HashMap<>();
        Map<String,Object> queryMap=new HashMap<>();
        queryMap.put("name","%"+name+"%");
        if(gradeId!=null)
        queryMap.put("gradeId",gradeId);
        queryMap.put("offset",page.getOffset());
        queryMap.put("pageSize",page.getRows());
        ret.put("rows",clazzService.findClazzList(queryMap));
        ret.put("total",clazzService.getTotal(queryMap));
        return  ret;

    }
    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> add(Clazz clazz){
        Map<String,String> ret=new HashMap<>();
        if(StringUtils.isEmpty(clazz.getName())){
            ret.put("type","fail");
            ret.put("msg","班级名不能为空");
            return ret;
        }
        if(StringUtils.isEmpty(clazz.getGradeId())){
            ret.put("type","fail");
            ret.put("msg","年级名不能为空");
            return ret;
        }
        if(clazzService.insertClazz(clazz)<=0){
            ret.put("type","fail");
            ret.put("msg","班级添加失败");
            return ret;
        }
        ret.put("type","success");
        ret.put("msg","班级添加成功");
        return ret;
    }
    /*
     * 修改页面
     *
     * */
    @RequestMapping(value = "/edit" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> edit(Clazz clazz){
        System.out.println("收到gradeId:   "+clazz);
        Map<String,String> ret=new HashMap<>();
        if(StringUtils.isEmpty(clazz.getName())){
            ret.put("type","fail");
            ret.put("msg","班级名不能为空");
            return ret;
        }
        if(clazzService.edit(clazz)<=0){
            System.out.println("接收到年级： "+clazz);
            ret.put("type","fail");
            ret.put("msg","年级修改失败");
            return ret;
        }
        ret.put("type","success");
        ret.put("msg","班级添加成功");
        return ret;
    }
    @RequestMapping(value = "/delete" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> delete(@RequestParam(value = "ids[]",required = true) Long[] ids){
        Map<String,String> ret=new HashMap<>();
        for(long id:ids){
            if(clazzService.delete(id)<=0){
                ret.put("type","fail");
                ret.put("msg","删除班级失败");
                return ret;
            }
        }
        ret.put("type","success");
        ret.put("msg","班级删除成功");
        return ret;
    }
}
