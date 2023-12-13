package com.ruoyi.spm.mapper;

import java.util.List;
import com.ruoyi.spm.domain.SpmCourses;

/**
 * 课程Mapper接口
 * 
 * @author Antillles
 * @date 2023-12-13
 */
public interface SpmCoursesMapper 
{
    /**
     * 查询课程
     * 
     * @param id 课程主键
     * @return 课程
     */
    public SpmCourses selectSpmCoursesById(String id);

    /**
     * 查询课程列表
     * 
     * @param spmCourses 课程
     * @return 课程集合
     */
    public List<SpmCourses> selectSpmCoursesList(SpmCourses spmCourses);

    /**
     * 新增课程
     * 
     * @param spmCourses 课程
     * @return 结果
     */
    public int insertSpmCourses(SpmCourses spmCourses);

    /**
     * 修改课程
     * 
     * @param spmCourses 课程
     * @return 结果
     */
    public int updateSpmCourses(SpmCourses spmCourses);

    /**
     * 删除课程
     * 
     * @param id 课程主键
     * @return 结果
     */
    public int deleteSpmCoursesById(String id);

    /**
     * 批量删除课程
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSpmCoursesByIds(String[] ids);
}
