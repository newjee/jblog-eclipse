package com.poscodx.jblog.security;

import com.poscodx.jblog.vo.UserVo;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminInterceptor implements HandlerInterceptor {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            String requestURI = request.getRequestURI();
            HttpSession session = request.getSession();
            UserVo userVo = (UserVo) session.getAttribute("authUser");
            System.out.println("********userVo " + userVo);
            Pattern pattern = Pattern.compile("/jblog03/([^/]+)/");
            Matcher matcher = pattern.matcher(requestURI);

            String blogId = "";
            if (matcher.find()) { // 패턴과 일치하는 부분 찾기
                blogId = matcher.group(1);
            }


            System.out.println("********blogId"+blogId);
            
            System.out.println("********userVo.getId() "+userVo.getId());
            if(userVo == null || !(userVo.getId().equals(blogId))) { // 권한 없는 경우
                System.out.println("자신의 블로그 관리자 페이지에만 들어갈 수 있습니다.");
                request.getRequestDispatcher("/WEB-INF/views/error/noAuth.jsp")
                        .forward(request, response);
                return false;
            }

            return true;
        }
    }
