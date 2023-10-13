package com.poscodx.jblog.vo;

import java.util.Optional;

public class BlogVo {

    private String blogTitle;
    private String blogImage;
    private String blogId;
    private Long categoryNo;
    private String categoryName;
    private String categoryDesc;
    private Long postNo;
    private String postTitle;
    private String postContents;
    private String postRegDate;
    private Long postCount;

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
                ", postRegDate='" + postRegDate + '\'' +
                ", postCount=" + postCount +
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

    public Long getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(Long categoryNo) {
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

    public Long getPostNo() {
        return postNo;
    }

    public void setPostNo(Long postNo) {
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

    public String getPostRegDate() {
        return postRegDate;
    }

    public void setPostRegDate(String postRegDate) {
        this.postRegDate = postRegDate;
    }

    public Long getPostCount() {
        return postCount;
    }

    public void setPostCount(Long postCount) {
        this.postCount = postCount;
    }
}
