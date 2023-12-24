package com.ruoyi.spm.domain;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.time.LocalDateTime;

/**
 * 课程章节对象 spm_course_chapter
 *
 * @author Antillles
 * @date 2023-12-13
 */
public class SpmCourseChapter extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 章节UUID */
    private String id;

    /** 归属课程课程号 */
    private String courseId;

    /** 章节名称 */
    @Excel(name = "章节名称")
    private String chapterName;

    /** 排序优先级，数字越小越靠前 */
    private int priority;

    /** 删除标志（0代表存在 1代表删除） */
    private char delFlag;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    public void setCourseId(String courseId)
    {
        this.courseId = courseId;
    }

    public String getCourseId()
    {
        return courseId;
    }

    public void setChapterName(String chapterName)
    {
        this.chapterName = chapterName;
    }

    public String getChapterName()
    {
        return chapterName;
    }

    public void setPriority(int priority)
    {
        this.priority = priority;
    }

    public int getPriority()
    {
        return priority;
    }

    public void setDelFlag(char delFlag)
    {
        this.delFlag = delFlag;
    }

    public char getDelFlag()
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("courseId", getCourseId())
                .append("chapterName", getChapterName())
                .append("priority", getPriority())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
