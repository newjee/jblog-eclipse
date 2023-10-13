package com.poscodx.jblog.controller;

import com.poscodx.jblog.exception.BlogNotFoundException;
import com.poscodx.jblog.exception.FileUploadServiceException;
import com.poscodx.jblog.security.Auth;
import com.poscodx.jblog.security.AuthUser;
import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.service.FileUploadService;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.PostVo;
import com.poscodx.jblog.vo.UserVo;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller

@RequestMapping("/{id:^(?!assets|user|admin).*$}")
public class BlogController {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private BlogService blogService;

    @Autowired
    private FileUploadService fileUploadService;

    @ExceptionHandler(BlogNotFoundException.class)
    public String handleBlogNotFoundException() {
        return "error/notExist"; // "notExist.jsp"를 렌더링
    }

    @RequestMapping(value = {
            "",
            "/{categoryNo:^(?!assets|user|admin).*$}",
            "/{categoryNo:^(?!assets|user|admin).*$}/{postNo:^(?!assets|user|admin).*$}"})
    public String index(
            @PathVariable("id") String blogId,
            @PathVariable("categoryNo") Optional<Long> categoryNo,
            @PathVariable("postNo") Optional<Long> postNo,
            @ModelAttribute BlogVo blogVo,
            Model model) {

//        Map<String, Object> blogMap = new HashMap<>();
        System.out.println("@@@@@@@ " + blogVo);

        // blogTitle 매핑
        blogVo.setBlogId(blogId);
        System.out.println("@@@@@@@@ " + blogVo);
        blogVo = blogService.getBlogMainContent(blogVo);


        // default category 매핑
        List<BlogVo> categoryList = blogService.getBlogCategoryList(blogVo);
        System.out.println("@@@@@@@@ " + categoryList);
        Long firstCategoryNo = categoryList.get(0).getCategoryNo();
        System.out.println("@@@@@@@@ " + firstCategoryNo);
        blogVo.setCategoryNo(firstCategoryNo);
        System.out.println("@@@@@@@@ " + blogVo);

        // default post 매핑
        List<BlogVo> postList = blogService.getBlogPostList(blogVo);
        System.out.println("@@@@@@@@ " + postList);
        Long firstPostNo = postList.get(0).getPostNo();
        System.out.println("@@@@@@@@ " + firstPostNo);
        blogVo.setPostNo(firstPostNo);
        System.out.println("@@@@@@@@ " + blogVo);

        Long postIndex = (long) 0; // post list의 index

        model.addAttribute("blogVo", blogVo);
        System.out.println("%%%%%%%%%" + blogVo);

        //**************************사용자 id 처리 // 없는 id
        if (!categoryNo.isPresent() && !postNo.isPresent()) {
            System.out.println(">> no cateNo no postNo");
            // 경로에 카테고리 번호와 포스트 번호가 없을 때
            // 기본값  포스트 뿌려주기
//            // blogCategory List
//            blogMap.put("categoryMap", blogService.getBlogCategoryList(blogVo));
//            blogService.getBlogPostList(blogVo);
//            // blogPost List
//            blogMap.put("postMap", blogService.getBlogPostList(blogVo));
//            // blogPost Content One
            System.out.println(">>>>> main map >>>>>" + blogVo);

        } else if (categoryNo.isPresent() && !postNo.isPresent()) {
            // 경로에 카테고리 번호가 있고 포스트 번호가 없을 때
            // 카테고리 이동 -> post
            System.out.println(">>>> blogController > blogMap > catemap start ");

            //카테고리 no 매핑
            blogVo.setCategoryNo(categoryNo.get());

        } else if (!categoryNo.isPresent() && postNo.isPresent()) {
            // 경로에 카테고리 번호가 없고 포스트 번호가 있을 때
            // 포스트 view
            System.out.println("카테고리nonononono");
            blogVo.setPostNo(postNo.get());

        } else if (categoryNo.isPresent() && postNo.isPresent()) {
            // 경로에 카테고리 번호와 포스트 번호가 모두 있을 때
            // 포스트 번호에 맞는 게시물 출력

            blogVo.setCategoryNo(categoryNo.get());
            blogVo.setPostNo(postNo.get());

            for(BlogVo data : postList) {
                if(data.getPostNo() == postNo.get()) break;
                postIndex++;
            }

        } else {
            return "error";
        }

        model.addAttribute("postIndex", postIndex);
        // blogCategory List
        model.addAttribute("categoryList", blogService.getBlogCategoryList(blogVo));
        System.out.println("!!!!!!!!! blogService.getBlogCategoryList(blogVo) !!!!! " + blogService.getBlogCategoryList(blogVo));
        System.out.println("!!!!!!model!!!!!!!  " + model);
        // blogPost List
        model.addAttribute("PostList", blogService.getBlogPostList(blogVo));
        System.out.println("!!!!!!model!!!!!!!   " + model);

        System.out.println(">>>>> blogVo >>>>>" + blogVo);

        return "blog/main";
    }


    @Auth
    // 아이디도 동일한 지 체크
    @RequestMapping("/admin/basic")
    public String adminBasic(@PathVariable("id") String blogId,
                             @ModelAttribute BlogVo blogVo,
                             Model model) {
        blogVo.setBlogId(blogId);

        model.addAttribute("blogVo", blogService.getBlogMainContent(blogVo));
        System.out.println(">*>**> blogVo >>>>>" + blogVo);

//		BlogVo blogVo = blogService.getBlogMainContent(blogId);
//		model.addAttribute("blogvo")
        return "blog/admin-basic";
    }

    @RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
    public String adminBasic(@PathVariable("id") String blogId,
                             @ModelAttribute BlogVo blogVo,
                             MultipartFile file) {
        System.out.println("######***** blogvo >>>>>" + blogVo);

        String blogImage = fileUploadService.restore(file);

        if (blogImage == null) {
            blogImage = blogVo.getBlogImage();
        }
        blogVo.setBlogImage(blogImage);

        System.out.println("***** blogImage >>>>>" + blogImage);
        System.out.println("***** blogvo >>>>>" + blogVo);

        blogService.adminBasicUpdate(blogVo);
        return "redirect:/" + blogId + "/admin/basic";

    }

    @RequestMapping("/admin/category")
    public String adminCategory(@PathVariable("id") String blogId,
                                @ModelAttribute BlogVo blogVo,
                                Model model) {
        blogVo.setBlogId(blogId);
        model.addAttribute("blogVo", blogService.getBlogMainContent(blogVo));

        List<BlogVo> categoryList = blogService.getAdminCategory(blogVo);

        model.addAttribute("categoryList", categoryList);
        System.out.println(">>>>> BlogController > categoryList>" + categoryList);

        return "blog/admin-category";
    }

    @RequestMapping(value = "/admin/category", method = RequestMethod.POST)
    public String adminCategory(@PathVariable("id") String blogId,
                                @ModelAttribute BlogVo blogVo) {
        blogVo.setBlogId(blogId);
        blogService.insertCategory(blogVo);

        return "redirect:/" + blogId + "/admin/category";
    }

    @RequestMapping(value = "/admin/category/delete/{categoryNo}/{postCount}")
    public String adminCategoryDelete(@PathVariable("id") String blogId,
                                      @PathVariable("categoryNo") Long categoryNo,
                                      @PathVariable("postCount") Long postCount, // categoryId 매핑
                                      @ModelAttribute BlogVo blogVo) {
        System.out.println("!!!!!!!! blogVo !!!!!!!! " + blogVo);
        blogVo.setBlogId(blogId);
        blogVo.setCategoryNo(categoryNo);
        blogVo.setPostCount(postCount);

        blogService.deleteCategory(blogVo);

        return "redirect:/" + blogId + "/admin/category";
    }

    @RequestMapping("/admin/write")
    public String adminWrite(@PathVariable("id") String blogId,
                             @ModelAttribute BlogVo blogVo,
                             Model model) {
        blogVo.setBlogId(blogId);
        model.addAttribute("blogVo", blogService.getBlogMainContent(blogVo));

        List<BlogVo> categoryList = blogService.getAdminCategory(blogVo);
        model.addAttribute("categoryList", categoryList);

        return "blog/admin-write";
    }

    @RequestMapping(value = "/admin/write", method = RequestMethod.POST)
    public String adminWrite(@PathVariable("id") String blogId,
                             @RequestParam("category") Long categoryNo,
                             @ModelAttribute BlogVo blogVo) {
        blogVo.setBlogId(blogId);
        blogVo.setCategoryNo(categoryNo);
        blogService.insertAdminPost(blogVo);

        return "redirect:/" + blogId + "/admin/write";
    }
}



