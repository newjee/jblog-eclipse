package com.poscodx.jblog.security;

import com.poscodx.jblog.vo.UserVo;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //1. handler type check
        if (!(handler instanceof HandlerMethod)) {
            //1-1. DefaultServletHander가 처리하는 경우 (static 자원, /assets/**)
            return true;
        }

        //2. casting
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        //3. Handler Method의 @Auth 받아오기!
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
        //3-1 Handler class의 @auth 가져오기
//        Auth authClass = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);


        //4. 인증 처리 여부 확인
        //4-1. @Auth가 없는 경우 -> 인증이 필요없는 요청이므로 true 처리
        if (auth == null) {
            return true;
        }

        //4-2. @Auth가 있는 경우, 인증(Authenfication) 확인 -> session 확인
        HttpSession session = request.getSession();
        UserVo authUser = (UserVo) session.getAttribute("authUser");

        if (authUser == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false;
        }

        //5. 권한 (Authorization) check : @Auth의 Role 가져오기 (ADMIN/USER)

        String role = auth.Role();
        String authUserRole = authUser.getRole();
        System.out.println("role>" + role);
        System.out.println("authUserRole>" + authUserRole);

        if (!role.equals(authUserRole)) {

            response.sendRedirect(request.getContextPath() + "/");
            return false;

        }
        //6. 인증 확인!
        return true;
    }
}
