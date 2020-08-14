package com.mz.domain;

import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Objects;

public class Student {
   private String username;
   private String password;
   private String sex;
   private String remark;
   private String  sn;
   private String photo;
   private  Long clazzId;
   private Long id;

    public Student(String username, String password, String sex, String remark, String sn, String photo, Long clazzId, Long id) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.remark = remark;
        this.sn = sn;
        this.photo = photo;
        this.clazzId = clazzId;
        this.id = id;
    }

    public Student() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getClazzId() {
        return clazzId;
    }

    public void setClazzId(Long clazzId) {
        this.clazzId = clazzId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", remark='" + remark + '\'' +
                ", sn='" + sn + '\'' +
                ", photo='" + photo + '\'' +
                ", clazzId=" + clazzId +
                ", id=" + id +
                '}';
    }
}
