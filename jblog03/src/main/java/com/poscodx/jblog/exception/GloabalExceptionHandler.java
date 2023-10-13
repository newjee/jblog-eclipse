package com.poscodx.jblog.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class GloabalExceptionHandler {

    private static final Log logger = LogFactory.getLog(GloabalExceptionHandler.class);


    @ExceptionHandler(Exception.class)
    public String handlerException(Model model, Exception e) {
        //1. Logging
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        System.out.println(errors.toString());
        logger.error(errors.toString());

//        System.out.println(e);
        e.printStackTrace();

        //2. Apologize
        model.addAttribute("errors", errors.toString());
        return "error/exception";
    }


    @ExceptionHandler(BlogNotFoundException.class)
    public String handleBlogNotFoundException() {
        return "error/notExist"; // "notExist.jsp"를 렌더링
    }
}
