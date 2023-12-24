package com.ruoyi.spm.mapper;

import com.ruoyi.spm.domain.SpmCourseChapterContent;

import java.util.List;

/**
 * 课程章节内容Mapper接口
 * 
 * @author ruoyi
 * @date 2023-12-24
 */
public interface SpmCourseChapterContentMapper 
{
    /**
     * 查询课程章节内容
     * 
     * @param id 课程章节内容主键
     * @return 课程章节内容
     */
    public SpmCourseChapterContent selectSpmCourseChapterContentById(String id);

    /**
     * 查询课程章节内容列表
     * 
     * @param spmCourseChapterContent 课程章节内容
     * @return 课程章节内容集合
     */
    public List<SpmCourseChapterContent> selectSpmCourseChapterContentList(SpmCourseChapterContent spmCourseChapterContent);

    /**
     * 新增课程章节内容
     * 
     * @param spmCourseChapterContent 课程章节内容
     * @return 结果
     */
    public int insertSpmCourseChapterContent(SpmCourseChapterContent spmCourseChapterContent);

    /**
     * 修改课程章节内容
     * 
     * @param spmCourseChapterContent 课程章节内容
     * @return 结果
     */
    public int updateSpmCourseChapterContent(SpmCourseChapterContent spmCourseChapterContent);

    /**
     * 删除课程章节内容
     * 
     * @param id 课程章节内容主键
     * @return 结果
     */
    public int deleteSpmCourseChapterContentById(String id);

    /**
     * 批量删除课程章节内容
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSpmCourseChapterContentByIds(String[] ids);
}
