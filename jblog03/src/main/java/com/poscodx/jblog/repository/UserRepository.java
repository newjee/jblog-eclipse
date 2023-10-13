package com.poscodx.jblog.repository;

import com.poscodx.jblog.vo.UserVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    @Autowired
    private SqlSession sqlSession;

    public int insert(UserVo userVo){
        System.out.println(">>>>> userRepo >>>>>" +  userVo);

        return sqlSession.insert("user.insert", userVo);
    }

    public UserVo findByIdAndPassword(String id, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("password", password);
        System.out.println(">>>>>"+map);
        return sqlSession.selectOne("user.findByIdAndPassword", map);
    }

    public List<UserVo> getUsers() {
        return sqlSession.selectList("user.findUsers");
    }

    public boolean isIdAvailable(String id) {
        int count = sqlSession.selectOne("user.isIdAvailable", id);

        // 조회 결과를 확인하여 중복 여부를 판단
        return count == 0; // count가 0인 경우 중복되지 않는 것으로 판단

    }
}
