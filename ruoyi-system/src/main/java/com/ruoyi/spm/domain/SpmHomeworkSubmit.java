package com.ruoyi.spm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 作业提交对象 spm_homework_submit
 * 
 * @author ruoyi
 * @date 2023-12-23
 */
public class SpmHomeworkSubmit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** uuid */
    private String id;

    /** 作业id */
    @Excel(name = "作业id")
    private String homeworkId;

    /** 作业内容(url) */
    @Excel(name = "作业内容(url)")
    private String contentLink;

    /** 签到学生学号 */
    @Excel(name = "签到学生学号")
    private String studentId;

    /** 签到学生姓名 */
    @Excel(name = "签到学生姓名")
    private String studentName;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

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
    public void setHomeworkId(String homeworkId) 
    {
        this.homeworkId = homeworkId;
    }

    public String getHomeworkId() 
    {
        return homeworkId;
    }
    public void setContentLink(String contentLink) 
    {
        this.contentLink = contentLink;
    }

    public String getContentLink() 
    {
        return contentLink;
    }
    public void setStudentId(String studentId) 
    {
        this.studentId = studentId;
    }

    public String getStudentId() 
    {
        return studentId;
    }
    public void setStudentName(String studentName) 
    {
        this.studentName = studentName;
    }

    public String getStudentName() 
    {
        return studentName;
    }
    public void setSubmitTime(Date submitTime) 
    {
        this.submitTime = submitTime;
    }

    public Date getSubmitTime() 
    {
        return submitTime;
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
            .append("homeworkId", getHomeworkId())
            .append("contentLink", getContentLink())
            .append("studentId", getStudentId())
            .append("studentName", getStudentName())
            .append("submitTime", getSubmitTime())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
