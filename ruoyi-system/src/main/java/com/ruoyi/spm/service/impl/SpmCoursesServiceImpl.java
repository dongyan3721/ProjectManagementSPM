package com.ruoyi.spm.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.spm.mapper.SpmCoursesMapper;
import com.ruoyi.spm.domain.SpmCourses;
import com.ruoyi.spm.service.ISpmCoursesService;

/**
 * 课程Service业务层处理
 * 
 * @author Antillles
 * @date 2023-12-13
 */
@Service
public class SpmCoursesServiceImpl implements ISpmCoursesService 
{
    @Autowired
    private SpmCoursesMapper spmCoursesMapper;

    /**
     * 查询课程
     * 
     * @param id 课程主键
     * @return 课程
     */
    @Override
    public SpmCourses selectSpmCoursesById(String id)
    {
        return spmCoursesMapper.selectSpmCoursesById(id);
    }

    /**
     * 查询课程列表
     * 
     * @param spmCourses 课程
     * @return 课程
     */
    @Override
    public List<SpmCourses> selectSpmCoursesList(SpmCourses spmCourses)
    {
        return spmCoursesMapper.selectSpmCoursesList(spmCourses);
    }

    /**
     * 新增课程
     * 
     * @param spmCourses 课程
     * @return 结果
     */
    @Override
    public int insertSpmCourses(SpmCourses spmCourses)
    {
        spmCourses.setCreateTime(DateUtils.getNowDate());
        return spmCoursesMapper.insertSpmCourses(spmCourses);
    }

    /**
     * 修改课程
     * 
     * @param spmCourses 课程
     * @return 结果
     */
    @Override
    public int updateSpmCourses(SpmCourses spmCourses)
    {
        spmCourses.setUpdateTime(DateUtils.getNowDate());
        return spmCoursesMapper.updateSpmCourses(spmCourses);
    }

    /**
     * 批量删除课程
     * 
     * @param ids 需要删除的课程主键
     * @return 结果
     */
    @Override
    public int deleteSpmCoursesByIds(String[] ids)
    {
        return spmCoursesMapper.deleteSpmCoursesByIds(ids);
    }

    /**
     * 删除课程信息
     * 
     * @param id 课程主键
     * @return 结果
     */
    @Override
    public int deleteSpmCoursesById(String id)
    {
        return spmCoursesMapper.deleteSpmCoursesById(id);
    }
}
