package com.ruoyi.spm.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.spm.domain.SpmCourseChapterContent;
import com.ruoyi.spm.mapper.SpmCourseChapterContentMapper;
import com.ruoyi.spm.service.ISpmCourseChapterContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 课程章节内容Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-12-24
 */
@Service
public class SpmCourseChapterContentServiceImpl implements ISpmCourseChapterContentService
{
    @Autowired
    private SpmCourseChapterContentMapper spmCourseChapterContentMapper;

    /**
     * 查询课程章节内容
     * 
     * @param id 课程章节内容主键
     * @return 课程章节内容
     */
    @Override
    public SpmCourseChapterContent selectSpmCourseChapterContentById(String id)
    {
        return spmCourseChapterContentMapper.selectSpmCourseChapterContentById(id);
    }

    /**
     * 查询课程章节内容列表
     * 
     * @param spmCourseChapterContent 课程章节内容
     * @return 课程章节内容
     */
    @Override
    public List<SpmCourseChapterContent> selectSpmCourseChapterContentList(SpmCourseChapterContent spmCourseChapterContent)
    {
        return spmCourseChapterContentMapper.selectSpmCourseChapterContentList(spmCourseChapterContent);
    }

    /**
     * 新增课程章节内容
     * 
     * @param spmCourseChapterContent 课程章节内容
     * @return 结果
     */
    @Override
    public int insertSpmCourseChapterContent(SpmCourseChapterContent spmCourseChapterContent)
    {
        spmCourseChapterContent.setCreateTime(DateUtils.getNowDate());
        return spmCourseChapterContentMapper.insertSpmCourseChapterContent(spmCourseChapterContent);
    }

    /**
     * 修改课程章节内容
     * 
     * @param spmCourseChapterContent 课程章节内容
     * @return 结果
     */
    @Override
    public int updateSpmCourseChapterContent(SpmCourseChapterContent spmCourseChapterContent)
    {
        spmCourseChapterContent.setUpdateTime(DateUtils.getNowDate());
        return spmCourseChapterContentMapper.updateSpmCourseChapterContent(spmCourseChapterContent);
    }

    /**
     * 批量删除课程章节内容
     * 
     * @param ids 需要删除的课程章节内容主键
     * @return 结果
     */
    @Override
    public int deleteSpmCourseChapterContentByIds(String[] ids)
    {
        return spmCourseChapterContentMapper.deleteSpmCourseChapterContentByIds(ids);
    }

    /**
     * 删除课程章节内容信息
     * 
     * @param id 课程章节内容主键
     * @return 结果
     */
    @Override
    public int deleteSpmCourseChapterContentById(String id)
    {
        return spmCourseChapterContentMapper.deleteSpmCourseChapterContentById(id);
    }
}
