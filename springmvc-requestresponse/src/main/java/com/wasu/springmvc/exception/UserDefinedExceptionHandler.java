package com.wasu.springmvc.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ZHANGLEI
 * @date 2021/11/30 9:28
 */
@ControllerAdvice
public class UserDefinedExceptionHandler {
    @ExceptionHandler(ArithmeticException.class)
    public String arithmaticException(Exception e, Model model) {
        model.addAttribute("e", e);
        return "error";
    }
}
