package com.ruoyi.spm.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class SpmHomework extends BaseEntity {
    private String id;
    private String courseId;
    private String title;
    private String content;
    private String appendixLink;
    private Date beginTime;
    private Date endTime;
    private String delFlag;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("courseId", courseId)
                .append("title", title)
                .append("content", content)
                .append("appendixLink", appendixLink)
                .append("beginTime", beginTime)
                .append("endTime", endTime)
                .append("delFlag", delFlag)
                .toString();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public SpmHomework() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAppendixLink() {
        return appendixLink;
    }

    public void setAppendixLink(String appendixLink) {
        this.appendixLink = appendixLink;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}
