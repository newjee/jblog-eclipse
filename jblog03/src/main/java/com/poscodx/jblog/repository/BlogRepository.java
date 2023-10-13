package com.poscodx.jblog.repository;

import com.poscodx.jblog.vo.BlogVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlogRepository {

    @Autowired
    private SqlSession sqlSession;

    public BlogVo findBlog(BlogVo blogVo) {

        System.out.println(">>>>> BlogRepo : input >>>>>" + blogVo);

        System.out.println(">>>>> BlogRepo : sqlQuery  >>>>>" +  sqlSession.selectOne("blog.findBlog", blogVo));

        return sqlSession.selectOne("blog.findBlog", blogVo);

    }

    public List<BlogVo> findCategoryList(BlogVo blogVo) {
        return sqlSession.selectList("blog.findCategoryList", blogVo );
    }

    public List<BlogVo> findPostList(BlogVo blogVo) {
        return sqlSession.selectList("blog.findPostList", blogVo );
    }

    public BlogVo findPostContent(BlogVo blogVo) {
        System.out.println(">>>>> BlogRepo : blogId >>>>>" + blogVo);

        System.out.println(">>>>> BlogRepo >>>>>" +  sqlSession.selectOne("blog.findPostContent", blogVo));

        return sqlSession.selectOne("blog.findPostContent", blogVo);

    }
    public boolean insert(BlogVo blogVo) {
        try {
            sqlSession.insert("blog.insert", blogVo);
            sqlSession.insert("insertCategory", blogVo);
            sqlSession.insert("insertPost", blogVo);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 하나라도 실패하면 false 반환
        }
    }

    public void updateBasic(BlogVo blogVo) {
        System.out.println(">>>>> BlogRepo > updateBasic >>>>>" + blogVo);
        sqlSession.update("blog.updateBasic", blogVo);


    }

    public List<BlogVo> findAdminCategory(BlogVo blogVo) {
        return sqlSession.selectList("blog.findAdminCategory", blogVo );

    }

    public void insertCategory(BlogVo blogVo) {
        sqlSession.insert("blog.InsertAdminCategory", blogVo);

    }

    public void insertAdminPost(BlogVo blogVo) {
        sqlSession.insert("blog.insertAdminPost", blogVo);

    }

    public void deleteCategory(BlogVo blogVo) {
        sqlSession.delete("blog.deleteCategory", blogVo);

    }

    public void deletePost(BlogVo blogVo) {

        sqlSession.delete("blog.deletePost", blogVo);

    }
}
