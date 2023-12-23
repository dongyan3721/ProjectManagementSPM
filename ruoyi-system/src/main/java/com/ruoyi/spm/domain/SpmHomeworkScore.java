package com.ruoyi.spm.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 作业得分对象 spm_homework_score
 * 
 * @author ruoyi
 * @date 2023-12-24
 */
public class SpmHomeworkScore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** uuid */
    private String id;

    /** 作业id */
    @Excel(name = "作业id")
    private String homeworkId;

    /** 提交作业id */
    @Excel(name = "提交作业id")
    private String submitHomeworkId;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private String studentId;

    /** 签到学生姓名 */
    @Excel(name = "签到学生姓名")
    private String studentName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double score;

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
    public void setSubmitHomeworkId(String submitHomeworkId) 
    {
        this.submitHomeworkId = submitHomeworkId;
    }

    public String getSubmitHomeworkId() 
    {
        return submitHomeworkId;
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
    public void setScore(Double score)
    {
        this.score = score;
    }

    public Double getScore()
    {
        return score;
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
            .append("submitHomeworkId", getSubmitHomeworkId())
            .append("studentId", getStudentId())
            .append("studentName", getStudentName())
            .append("score", getScore())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
