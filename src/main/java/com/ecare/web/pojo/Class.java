package com.ecare.web.pojo;

import java.io.Serializable;
import java.util.Date;

public class Class implements Serializable{
    private static final long serialVersionUID = 7339226119888783290L;
    private Integer classId;

    private String className;

    private String classDesc;

    private Integer classType;

    private Integer classCreaterId;

    private Date classCreateTime;

    private Date classEditTime;

    private Integer classMembership;

    private Integer classViews;

    private String re1;

    private String re2;

    private String re3;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getClassDesc() {
        return classDesc;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc == null ? null : classDesc.trim();
    }

    public Integer getClassType() {
        return classType;
    }

    public void setClassType(Integer classType) {
        this.classType = classType;
    }

    public Integer getClassCreaterId() {
        return classCreaterId;
    }

    public void setClassCreaterId(Integer classCreaterId) {
        this.classCreaterId = classCreaterId;
    }

    public Date getClassCreateTime() {
        return classCreateTime;
    }

    public void setClassCreateTime(Date classCreateTime) {
        this.classCreateTime = classCreateTime;
    }

    public Date getClassEditTime() {
        return classEditTime;
    }

    public void setClassEditTime(Date classEditTime) {
        this.classEditTime = classEditTime;
    }

    public Integer getClassMembership() {
        return classMembership;
    }

    public void setClassMembership(Integer classMembership) {
        this.classMembership = classMembership;
    }

    public Integer getClassViews() {
        return classViews;
    }

    public void setClassViews(Integer classViews) {
        this.classViews = classViews;
    }

    public String getRe1() {
        return re1;
    }

    public void setRe1(String re1) {
        this.re1 = re1 == null ? null : re1.trim();
    }

    public String getRe2() {
        return re2;
    }

    public void setRe2(String re2) {
        this.re2 = re2 == null ? null : re2.trim();
    }

    public String getRe3() {
        return re3;
    }

    public void setRe3(String re3) {
        this.re3 = re3 == null ? null : re3.trim();
    }
}