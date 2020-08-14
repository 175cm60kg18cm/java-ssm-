package com.mz.exceptionhandler;

import com.mz.exception.NameException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(value = NameException.class)
    public ModelAndView myNameEcxeptionHandler(Exception ex){
        ModelAndView mv=new ModelAndView();
        mv.addObject("msg","名字错误不为zs");
        mv.setViewName("nameError");
        return  mv;
    }
    @ExceptionHandler
    public ModelAndView defaultHanler(Exception ex){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("defaultError");
        return modelAndView;
    }
}
