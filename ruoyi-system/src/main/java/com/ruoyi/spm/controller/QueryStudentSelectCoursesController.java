package com.ruoyi.spm.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.spm.domain.SpmCourses;
import com.ruoyi.spm.service.IQueryStudentSelectCoursesService;
import com.ruoyi.spm.service.ISpmCoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Santa Antilles
 * @Date 2023/12/14-14:13
 */
@RestController
@RequestMapping("/spm/student-courses")
public class QueryStudentSelectCoursesController extends BaseController {

    @Autowired
    private IQueryStudentSelectCoursesService queryStudentSelectCoursesService;

    @Autowired
    private ISpmCoursesService spmCoursesService;

    @PostMapping("/query")
    public TableDataInfo queryCertainStudentSelectedCourses(){
        return getDataTable(queryStudentSelectCoursesService.getSelectedCourses(SecurityUtils.getLoginUser().getUsername()));
    }


    @PreAuthorize("@ss.hasPermi('spm:study:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(spmCoursesService.selectSpmCoursesById(id));
    }
}
