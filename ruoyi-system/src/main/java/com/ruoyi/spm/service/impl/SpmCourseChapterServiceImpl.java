package com.ruoyi.spm.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.spm.mapper.SpmCourseChapterMapper;
import com.ruoyi.spm.domain.SpmCourseChapter;
import com.ruoyi.spm.service.ISpmCourseChapterService;

/**
 * 课程章节Service业务层处理
 *
 * @author Antillles
 * @date 2023-12-13
 */
@Service
public class SpmCourseChapterServiceImpl implements ISpmCourseChapterService
{
    @Autowired
    private SpmCourseChapterMapper spmCourseChapterMapper;

    @Override
    public SpmCourseChapter selectSpmCourseChapterById(String id)
    {
        return spmCourseChapterMapper.selectSpmCourseChapterById(id);
    }

    @Override
    public List<SpmCourseChapter> selectSpmCourseChapterList(SpmCourseChapter spmCourseChapter)
    {
        return spmCourseChapterMapper.selectSpmCourseChapterList(spmCourseChapter);
    }

    @Override
    public int insertSpmCourseChapter(SpmCourseChapter spmCourseChapter)
    {
        spmCourseChapter.setCreateTime(DateUtils.getNowDate());
        return spmCourseChapterMapper.insertSpmCourseChapter(spmCourseChapter);
    }

    @Override
    public int updateSpmCourseChapter(SpmCourseChapter spmCourseChapter)
    {
        spmCourseChapter.setUpdateTime(DateUtils.getNowDate());
        return spmCourseChapterMapper.updateSpmCourseChapter(spmCourseChapter);
    }

    @Override
    public int deleteSpmCourseChapterByIds(String[] ids)
    {
        return spmCourseChapterMapper.deleteSpmCourseChapterByIds(ids);
    }

    @Override
    public int deleteSpmCourseChapterById(String id)
    {
        return spmCourseChapterMapper.deleteSpmCourseChapterById(id);
    }
}
