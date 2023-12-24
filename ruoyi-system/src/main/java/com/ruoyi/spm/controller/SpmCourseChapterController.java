package com.ruoyi.spm.controller;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.spm.domain.SpmCourseChapter;
import com.ruoyi.spm.service.ISpmCourseChapterService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 课程章节Controller
 *
 * @author ruoyi
 * @date 2023-12-23
 */
@RestController
@RequestMapping("/system/chapter")
public class SpmCourseChapterController extends BaseController
{
    @Autowired
    private ISpmCourseChapterService spmCourseChapterService;

    /**
     * 查询课程章节列表
     */
    @PreAuthorize("@ss.hasPermi('system:chapter:list')")
    @GetMapping("/list")
    public TableDataInfo list(SpmCourseChapter spmCourseChapter)
    {
        startPage();
        List<SpmCourseChapter> list = spmCourseChapterService.selectSpmCourseChapterList(spmCourseChapter);
        return getDataTable(list);
    }

    /**
     * 导出课程章节列表
     */
    @PreAuthorize("@ss.hasPermi('system:chapter:export')")
    @Log(title = "课程章节", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SpmCourseChapter spmCourseChapter)
    {
        List<SpmCourseChapter> list = spmCourseChapterService.selectSpmCourseChapterList(spmCourseChapter);
        ExcelUtil<SpmCourseChapter> util = new ExcelUtil<SpmCourseChapter>(SpmCourseChapter.class);
        util.exportExcel(response, list, "课程章节数据");
    }

    /**
     * 获取课程章节详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:chapter:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(spmCourseChapterService.selectSpmCourseChapterById(id));
    }

    /**
     * 新增课程章节
     */
    @PreAuthorize("@ss.hasPermi('system:chapter:add')")
    @Log(title = "课程章节", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SpmCourseChapter spmCourseChapter)
    {
        return toAjax(spmCourseChapterService.insertSpmCourseChapter(spmCourseChapter));
    }

    /**
     * 修改课程章节
     */
    @PreAuthorize("@ss.hasPermi('system:chapter:edit')")
    @Log(title = "课程章节", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SpmCourseChapter spmCourseChapter)
    {
        return toAjax(spmCourseChapterService.updateSpmCourseChapter(spmCourseChapter));
    }

    /**
     * 删除课程章节
     */
    @PreAuthorize("@ss.hasPermi('system:chapter:remove')")
    @Log(title = "课程章节", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(spmCourseChapterService.deleteSpmCourseChapterByIds(ids));
    }
}
