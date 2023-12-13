package com.ruoyi.spm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程对象 spm_courses
 * 
 * @author Antillles
 * @date 2023-12-13
 */
public class SpmCourses extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程号 */
    private String id;

    /** 课程名 */
    @Excel(name = "课程名")
    private String courseName;

    /** 课程封面(url) */
    private String courseCoverLink;

    /** 任课老师工号 */
    @Excel(name = "任课老师工号")
    private String courseTeacherId;

    /** 任课老师名字 */
    @Excel(name = "任课老师名字")
    private String courseTeacherName;

    /** 启用学生评价，0不启用，1启用，默认不启用 */
    @Excel(name = "启用学生评价，0不启用，1启用，默认不启用")
    private String enableTeacherComment;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCourseName(String courseName) 
    {
        this.courseName = courseName;
    }

    public String getCourseName() 
    {
        return courseName;
    }
    public void setCourseCoverLink(String courseCoverLink) 
    {
        this.courseCoverLink = courseCoverLink;
    }

    public String getCourseCoverLink() 
    {
        return courseCoverLink;
    }
    public void setCourseTeacherId(String courseTeacherId) 
    {
        this.courseTeacherId = courseTeacherId;
    }

    public String getCourseTeacherId() 
    {
        return courseTeacherId;
    }
    public void setCourseTeacherName(String courseTeacherName) 
    {
        this.courseTeacherName = courseTeacherName;
    }

    public String getCourseTeacherName() 
    {
        return courseTeacherName;
    }
    public void setEnableTeacherComment(String enableTeacherComment) 
    {
        this.enableTeacherComment = enableTeacherComment;
    }

    public String getEnableTeacherComment() 
    {
        return enableTeacherComment;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("courseName", getCourseName())
            .append("courseCoverLink", getCourseCoverLink())
            .append("courseTeacherId", getCourseTeacherId())
            .append("courseTeacherName", getCourseTeacherName())
            .append("enableTeacherComment", getEnableTeacherComment())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
