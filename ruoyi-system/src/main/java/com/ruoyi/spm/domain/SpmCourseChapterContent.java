package com.ruoyi.spm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程章节内容对象 spm_course_chapter_content
 * 
 * @author ruoyi
 * @date 2023-12-24
 */
public class SpmCourseChapterContent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** uuid */
    private String id;

    /** 归属章节id */
    @Excel(name = "归属章节id")
    private String captchaId;

    /** 文件描述 */
    @Excel(name = "文件描述")
    private String description;

    /** 章节文件地址(url) */
    @Excel(name = "章节文件地址(url)")
    private String fileLink;

    /** 文件在章节内排序优先级 */
    @Excel(name = "文件在章节内排序优先级")
    private Long priority;

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
    public void setCaptchaId(String captchaId) 
    {
        this.captchaId = captchaId;
    }

    public String getCaptchaId() 
    {
        return captchaId;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setFileLink(String fileLink) 
    {
        this.fileLink = fileLink;
    }

    public String getFileLink() 
    {
        return fileLink;
    }
    public void setPriority(Long priority) 
    {
        this.priority = priority;
    }

    public Long getPriority() 
    {
        return priority;
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
            .append("captchaId", getCaptchaId())
            .append("description", getDescription())
            .append("fileLink", getFileLink())
            .append("priority", getPriority())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
