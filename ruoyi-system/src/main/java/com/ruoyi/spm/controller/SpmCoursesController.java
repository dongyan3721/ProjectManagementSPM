package com.ruoyi.spm.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.utils.CourseIdGenerator;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.spm.domain.SpmCourses;
import com.ruoyi.spm.service.ISpmCoursesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

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
    @PreAuthorize("@ss.hasAnyPermi('spm:courses:export')")
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
    @PreAuthorize("@ss.hasPermi('spm:courses:query,spm:study:detail')")
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
    public AjaxResult add(@RequestParam("courseContent") String spmCoursesString, @RequestParam("courseAvatar") MultipartFile file) throws IOException {
        List<String> split = StringUtils.str2List(file.getOriginalFilename(), "\\.", true, true);
        SpmCourses spmCourses = JSON.parseObject(spmCoursesString, SpmCourses.class);
        spmCourses.setId(CourseIdGenerator.generateString());
        String fileName = spmCourses.getCourseName() +"-"+ UUID.randomUUID()+"."+split.get(split.size()-1);
        String storePath = RuoYiConfig.getDownloadPath() + "course-avatar/" + fileName;
        file.transferTo(new File(storePath));
        spmCourses.setCourseCoverLink("/static/course-avatar/"+fileName);
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

    @PreAuthorize("@ss.hasPermi('spm:courses:edit')")
    @Log(title = "课程", businessType = BusinessType.UPDATE)
    @PutMapping("/avatar-modify")
    public AjaxResult avatarModify(@RequestParam("fileContent") MultipartFile file, @RequestParam("courseId") String courseId) throws IOException {
        List<String> split = StringUtils.str2List(file.getOriginalFilename(), "\\.", true, true);
        SpmCourses spmCourses = spmCoursesService.selectSpmCoursesById(courseId);
        String fileName = spmCourses.getCourseName() +"-"+ UUID.randomUUID()+"."+split.get(split.size()-1);
        String storePath = RuoYiConfig.getDownloadPath() + "course-avatar/" + fileName;
        file.transferTo(new File(storePath));
        SpmCourses courses = new SpmCourses();
        courses.setCourseCoverLink("/static/course-avatar/"+fileName);
        courses.setId(courseId);
        return toAjax(spmCoursesService.updateSpmCourses(courses));
    }
}
