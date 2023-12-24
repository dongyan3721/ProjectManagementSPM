package com.ruoyi.spm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 签到记录对象 spm_sign_record
 * 
 * @author Antilles
 * @date 2023-12-24
 */
public class SpmSignRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** uuid */
    private String id;

    /** 签到表id */
    @Excel(name = "签到表id")
    private String signId;

    /** 签到学生学号 */
    @Excel(name = "签到学生学号")
    private String studentId;

    /** 签到学生姓名 */
    @Excel(name = "签到学生姓名")
    private String studentName;

    /** 签到时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    @Excel(name = "签到时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signTime;

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
    public void setSignId(String signId) 
    {
        this.signId = signId;
    }

    public String getSignId() 
    {
        return signId;
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
    public void setSignTime(Date signTime) 
    {
        this.signTime = signTime;
    }

    public Date getSignTime() 
    {
        return signTime;
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
            .append("signId", getSignId())
            .append("studentId", getStudentId())
            .append("studentName", getStudentName())
            .append("signTime", getSignTime())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
