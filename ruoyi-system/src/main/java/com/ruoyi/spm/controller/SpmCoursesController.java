package com.ruoyi.spm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.spm.domain.SpmCourses;
import com.ruoyi.spm.service.ISpmCoursesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 课程Controller
 * 
 * @author Antillles
 * @date 2023-12-13
 */
@RestController
@RequestMapping("/spm/courses")
public class SpmCoursesController extends BaseController
{
    @Autowired
    private ISpmCoursesService spmCoursesService;

    /**
     * 查询课程列表
     */
    @PreAuthorize("@ss.hasPermi('spm:courses:list')")
    @GetMapping("/list")
    public TableDataInfo list(SpmCourses spmCourses)
    {
        startPage();
        List<SpmCourses> list = spmCoursesService.selectSpmCoursesList(spmCourses);
        return getDataTable(list);
    }

    /**
     * 导出课程列表
     */
    @PreAuthorize("@ss.hasPermi('spm:courses:export')")
    @Log(title = "课程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SpmCourses spmCourses)
    {
        List<SpmCourses> list = spmCoursesService.selectSpmCoursesList(spmCourses);
        ExcelUtil<SpmCourses> util = new ExcelUtil<SpmCourses>(SpmCourses.class);
        util.exportExcel(response, list, "课程数据");
    }

    /**
     * 获取课程详细信息
     */
    @PreAuthorize("@ss.hasPermi('spm:courses:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(spmCoursesService.selectSpmCoursesById(id));
    }

    /**
     * 新增课程
     */
    @PreAuthorize("@ss.hasPermi('spm:courses:add')")
    @Log(title = "课程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SpmCourses spmCourses)
    {
        return toAjax(spmCoursesService.insertSpmCourses(spmCourses));
    }

    /**
     * 修改课程
     */
    @PreAuthorize("@ss.hasPermi('spm:courses:edit')")
    @Log(title = "课程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SpmCourses spmCourses)
    {
        return toAjax(spmCoursesService.updateSpmCourses(spmCourses));
    }

    /**
     * 删除课程
     */
    @PreAuthorize("@ss.hasPermi('spm:courses:remove')")
    @Log(title = "课程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(spmCoursesService.deleteSpmCoursesByIds(ids));
    }
}
