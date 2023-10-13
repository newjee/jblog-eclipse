package com.poscodx.jblog.service;

import com.poscodx.jblog.exception.BlogNotFoundException;
import com.poscodx.jblog.repository.BlogRepository;
import com.poscodx.jblog.vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public BlogVo getBlogMainContent(BlogVo blogVo) {
        System.out.println(">>>>> BlogService > getBlogMainContent>" + blogVo);
//        BlogVo blogVo = ;
//        System.out.println(">>>>> BlogService >>>>>" + blogVo);

        BlogVo result = blogRepository.findBlog(blogVo);
        if (result == null) {
            throw new BlogNotFoundException("Blog not found");
        }
        return result;
    }

    public List<BlogVo> getBlogCategoryList(BlogVo blogVo) {
        System.out.println(">>>>> BlogService > getBlogCategoryList>" + blogVo);

        return blogRepository.findCategoryList(blogVo);
    }

    public List<BlogVo> getBlogPostList(BlogVo blogVo) {
        System.out.println(">>>>> BlogService > getBlogPostList>" + blogVo);

        return blogRepository.findPostList(blogVo);
    }

    public boolean insert(BlogVo blogVo) {
        System.out.println(">>>>> BlogService > insert>" + blogVo);

        return blogRepository.insert(blogVo);

    }

    public BlogVo getBlogPostContent(BlogVo blogVo) {
        return blogRepository.findPostContent(blogVo);

    }

    public void adminBasicUpdate(BlogVo blogVo) {
        blogRepository.updateBasic(blogVo);
    }

    public List<BlogVo> getAdminCategory(BlogVo blogVo) {
        System.out.println(">>>>> BlogService > getAdminCategory>" + blogVo);

        return blogRepository.findAdminCategory(blogVo);
    }

    public void insertCategory(BlogVo blogVo) {
        blogRepository.insertCategory(blogVo);
    }

    public void insertAdminPost(BlogVo blogVo) {
        blogRepository.insertAdminPost(blogVo);

    }

    public void deleteCategory(BlogVo blogVo) {

        if (blogVo.getPostCount() > 0) {
            blogRepository.deletePost(blogVo);
            blogRepository.deleteCategory(blogVo);
        } else {
            blogRepository.deleteCategory(blogVo);

        }
    }
}
