package com.ruoyi.spm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.spm.domain.SpmCourseChapterContent;
import com.ruoyi.spm.service.ISpmCourseChapterContentService;
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
 * 课程章节内容Controller
 * 
 * @author ruoyi
 * @date 2023-12-24
 */
@RestController
@RequestMapping("/system/content")
public class SpmCourseChapterContentController extends BaseController
{
    @Autowired
    private ISpmCourseChapterContentService spmCourseChapterContentService;

    /**
     * 查询课程章节内容列表
     */
    @PreAuthorize("@ss.hasPermi('system:content:list')")
    @GetMapping("/list")
    public TableDataInfo list(SpmCourseChapterContent spmCourseChapterContent)
    {
        startPage();
        List<SpmCourseChapterContent> list = spmCourseChapterContentService.selectSpmCourseChapterContentList(spmCourseChapterContent);
        return getDataTable(list);
    }

    /**
     * 导出课程章节内容列表
     */
    @PreAuthorize("@ss.hasPermi('system:content:export')")
    @Log(title = "课程章节内容", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SpmCourseChapterContent spmCourseChapterContent)
    {
        List<SpmCourseChapterContent> list = spmCourseChapterContentService.selectSpmCourseChapterContentList(spmCourseChapterContent);
        ExcelUtil<SpmCourseChapterContent> util = new ExcelUtil<SpmCourseChapterContent>(SpmCourseChapterContent.class);
        util.exportExcel(response, list, "课程章节内容数据");
    }

    /**
     * 获取课程章节内容详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:content:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(spmCourseChapterContentService.selectSpmCourseChapterContentById(id));
    }

    /**
     * 新增课程章节内容
     */
    @PreAuthorize("@ss.hasPermi('system:content:add')")
    @Log(title = "课程章节内容", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SpmCourseChapterContent spmCourseChapterContent)
    {
        return toAjax(spmCourseChapterContentService.insertSpmCourseChapterContent(spmCourseChapterContent));
    }

    /**
     * 修改课程章节内容
     */
    @PreAuthorize("@ss.hasPermi('system:content:edit')")
    @Log(title = "课程章节内容", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SpmCourseChapterContent spmCourseChapterContent)
    {
        return toAjax(spmCourseChapterContentService.updateSpmCourseChapterContent(spmCourseChapterContent));
    }

    /**
     * 删除课程章节内容
     */
    @PreAuthorize("@ss.hasPermi('system:content:remove')")
    @Log(title = "课程章节内容", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(spmCourseChapterContentService.deleteSpmCourseChapterContentByIds(ids));
    }
}
