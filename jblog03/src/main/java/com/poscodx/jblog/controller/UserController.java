package com.poscodx.jblog.controller;

import com.poscodx.jblog.exception.DuplicateIdException;
import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.service.UserService;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    // 1-1. 회원가입 폼 화면
    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String join(@ModelAttribute UserVo userVo) {
        return "user/join";
    }


    // 1-2. 회원가입 폼 입력 후 회원가입 성공 화면
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(@ModelAttribute @Valid UserVo userVo,
                       BindingResult result,
                       Model model,
                       HttpSession session
                       ) {

        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                System.out.println(error);}
//            System.out.println(result);
            model.addAllAttributes(result.getModel());
//            model.getAttribute("userVo", userVo);
            return "user/join";
        }

        try {
            userService.join(userVo);
            session.setAttribute("authUser", userVo);
            return "redirect:/user/joinsuccess";
        } catch (DuplicateIdException e) {
            // 중복 아이디 예외 처리
            model.addAttribute("errorMessage", "중복된 아이디입니다. 다시 입력하세요.");
            return "user/join";
        }
    }

    @RequestMapping(value = "/joinsuccess", method = RequestMethod.GET)
    public String joinsuccess(
            HttpSession session,
            @ModelAttribute BlogVo blogVo) {
        UserVo userVo = (UserVo) session.getAttribute("authUser");

        System.out.println("userId>>>>" + userVo.getId());
        blogVo.setBlogId(userVo.getId());
        System.out.println("userCon " + blogVo);
        blogService.insert(blogVo);

        //세션 종료
        session.invalidate();


        return "user/joinsuccess";
    }

    // 2-1. 로그인 화면
    @RequestMapping("/login")
    public String login() {
        return "user/login";
    }


}

