package com.ruoyi.spm.mapper;

import java.util.List;
import com.ruoyi.spm.domain.SpmStudent;

/**
 * 学生Mapper接口
 * 
 * @author ruoyi
 * @date 2023-12-23
 */
public interface SpmStudentMapper 
{
    /**
     * 查询学生
     * 
     * @param id 学生主键
     * @return 学生
     */
    public SpmStudent selectSpmStudentById(String id);

    /**
     * 查询学生列表
     * 
     * @param spmStudent 学生
     * @return 学生集合
     */
    public List<SpmStudent> selectSpmStudentList(SpmStudent spmStudent);

    /**
     * 新增学生
     * 
     * @param spmStudent 学生
     * @return 结果
     */
    public int insertSpmStudent(SpmStudent spmStudent);

    /**
     * 修改学生
     * 
     * @param spmStudent 学生
     * @return 结果
     */
    public int updateSpmStudent(SpmStudent spmStudent);

    /**
     * 删除学生
     * 
     * @param id 学生主键
     * @return 结果
     */
    public int deleteSpmStudentById(String id);

    /**
     * 批量删除学生
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSpmStudentByIds(String[] ids);
}
