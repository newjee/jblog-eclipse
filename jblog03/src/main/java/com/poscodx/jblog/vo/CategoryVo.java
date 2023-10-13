package com.poscodx.jblog.vo;

import java.util.Optional;

public class CategoryVo {

    private String blogTitle;
    private String blogImage;
    private String blogId;
    private Optional<Long> categoryNo;
    private String categoryName;
    private String categoryDesc;
    private Optional<Long> postNo;
    private String postTitle;
    private String postContents;

    @Override
    public String toString() {
        return "BlogVo{" +
                "blogTitle='" + blogTitle + '\'' +
                ", blogImage='" + blogImage + '\'' +
                ", blogId='" + blogId + '\'' +
                ", categoryNo=" + categoryNo +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDesc='" + categoryDesc + '\'' +
                ", postNo=" + postNo +
                ", postTitle='" + postTitle + '\'' +
                ", postContents='" + postContents + '\'' +
                '}';
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogImage() {
        return blogImage;
    }

    public void setBlogImage(String blogImage) {
        this.blogImage = blogImage;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public Optional<Long> getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(Optional<Long> categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public Optional<Long> getPostNo() {
        return postNo;
    }

    public void setPostNo(Optional<Long> postNo) {
        this.postNo = postNo;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContents() {
        return postContents;
    }

    public void setPostContents(String postContents) {
        this.postContents = postContents;
    }
}
