package com.poscodx.jblog.service;

import com.poscodx.jblog.exception.DuplicateIdException;
import com.poscodx.jblog.repository.UserRepository;
import com.poscodx.jblog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void join(UserVo userVo){
        // 중복 아이디 체크
        System.out.println("!!!!!"+ isIdAvailable(userVo.getId()));
        if (!isIdAvailable(userVo.getId())) {
            throw new DuplicateIdException("중복된 아이디입니다.");

        }

        // 중복 아이디가 아니면 가입 처리
        userRepository.insert(userVo);
        System.out.println(">>> userService : sql 후. > " + userVo);
    }

    public UserVo getUser(String id, String password){
        System.out.println(">>>>>"+id +password);
        return userRepository.findByIdAndPassword(id, password);
    }



    public List<UserVo> getUsers() {

        return userRepository.getUsers();

    }

    public boolean isIdAvailable(String id) {
        return userRepository.isIdAvailable(id);

    }
}
