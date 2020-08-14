package com.mz.domain;

public class Clazz {
    private long id;
    private long gradeId;

    public Clazz(long id, long gradeId, String remark, String name) {
        this.id = id;
        this.gradeId = gradeId;
        this.remark = remark;
        this.name = name;
    }

    private String remark;
    private String name;

    public Clazz() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGradeId() {
        return gradeId;
    }

    public void setGradeId(long gradeId) {
        this.gradeId = gradeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "id=" + id +
                ", gradeId=" + gradeId +
                ", remark='" + remark + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
