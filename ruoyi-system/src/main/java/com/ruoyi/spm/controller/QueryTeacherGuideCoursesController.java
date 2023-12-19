package com.ruoyi.spm.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.spm.service.IQueryTeacherGuideCoursesService;
import com.ruoyi.spm.service.ISpmCoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Santa Antilles
 * @Date 2023/12/17-15:16
 */
@RestController
@RequestMapping("/spm/teacher-guide")
public class QueryTeacherGuideCoursesController extends BaseController {
    @Autowired
    IQueryTeacherGuideCoursesService queryTeacherGuideCoursesService;

    @Autowired
    ISpmCoursesService spmCoursesService;

//    @PreAuthorize("@ss.hasPermi('spm:guide:list')")
    @GetMapping("/query")
    public TableDataInfo queryCertainTeacherGuideCourses(){
        return getDataTable(queryTeacherGuideCoursesService.getGuideCourses(SecurityUtils.getLoginUser().getUsername()));
    }

    @PreAuthorize("@ss.hasPermi('spm:teacher:detail')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id){
        return success(spmCoursesService.selectSpmCoursesById(id));
    }
}
