package com.poscodx.jblog.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class UserVo {

    @NotEmpty
    @Length(min=2, max=15)
    private String id;

    @NotEmpty
    @Length(min=4, max=16)
    private String password;

    @NotEmpty
    @Length(min=2, max=8)
    private String name;
    private String role;

    @Override
    public String toString() {
        return "UserVo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
