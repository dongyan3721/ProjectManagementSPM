package com.ruoyi.spm.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.spm.mapper.SpmStudentMapper;
import com.ruoyi.spm.domain.SpmStudent;
import com.ruoyi.spm.service.ISpmStudentService;

/**
 * 学生Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-12-23
 */
@Service
public class SpmStudentServiceImpl implements ISpmStudentService 
{
    @Autowired
    private SpmStudentMapper spmStudentMapper;

    /**
     * 查询学生
     * 
     * @param id 学生主键
     * @return 学生
     */
    @Override
    public SpmStudent selectSpmStudentById(String id)
    {
        return spmStudentMapper.selectSpmStudentById(id);
    }

    /**
     * 查询学生列表
     * 
     * @param spmStudent 学生
     * @return 学生
     */
    @Override
    public List<SpmStudent> selectSpmStudentList(SpmStudent spmStudent)
    {
        return spmStudentMapper.selectSpmStudentList(spmStudent);
    }

    /**
     * 新增学生
     * 
     * @param spmStudent 学生
     * @return 结果
     */
    @Override
    public int insertSpmStudent(SpmStudent spmStudent)
    {
        spmStudent.setCreateTime(DateUtils.getNowDate());
        return spmStudentMapper.insertSpmStudent(spmStudent);
    }

    /**
     * 修改学生
     * 
     * @param spmStudent 学生
     * @return 结果
     */
    @Override
    public int updateSpmStudent(SpmStudent spmStudent)
    {
        spmStudent.setUpdateTime(DateUtils.getNowDate());
        return spmStudentMapper.updateSpmStudent(spmStudent);
    }

    /**
     * 批量删除学生
     * 
     * @param ids 需要删除的学生主键
     * @return 结果
     */
    @Override
    public int deleteSpmStudentByIds(String[] ids)
    {
        return spmStudentMapper.deleteSpmStudentByIds(ids);
    }

    /**
     * 删除学生信息
     * 
     * @param id 学生主键
     * @return 结果
     */
    @Override
    public int deleteSpmStudentById(String id)
    {
        return spmStudentMapper.deleteSpmStudentById(id);
    }
}
