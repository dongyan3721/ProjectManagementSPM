package com.ruoyi.spm.mapper;

import com.ruoyi.spm.domain.SpmCourseChapter;

import java.util.List;

/**
 * 课程章节Mapper接口
 *
 * @author ruoyi
 * @date 2023-12-23
 */
public interface SpmCourseChapterMapper
{
    /**
     * 查询课程章节
     *
     * @param id 课程章节主键
     * @return 课程章节
     */
    public SpmCourseChapter selectSpmCourseChapterById(String id);

    /**
     * 查询课程章节列表
     *
     * @param spmCourseChapter 课程章节
     * @return 课程章节集合
     */
    public List<SpmCourseChapter> selectSpmCourseChapterList(SpmCourseChapter spmCourseChapter);

    /**
     * 新增课程章节
     *
     * @param spmCourseChapter 课程章节
     * @return 结果
     */
    public int insertSpmCourseChapter(SpmCourseChapter spmCourseChapter);

    /**
     * 修改课程章节
     *
     * @param spmCourseChapter 课程章节
     * @return 结果
     */
    public int updateSpmCourseChapter(SpmCourseChapter spmCourseChapter);

    /**
     * 删除课程章节
     *
     * @param id 课程章节主键
     * @return 结果
     */
    public int deleteSpmCourseChapterById(String id);

    /**
     * 批量删除课程章节
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSpmCourseChapterByIds(String[] ids);
}
