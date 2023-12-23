package com.ruoyi.spm.controller;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.spm.domain.SpmHomework;
import com.ruoyi.spm.service.ITeacherHomeworkService;
import com.ruoyi.spm.utils.FileStorageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/spm/teacher-homework")
public class TeacherCourseHomeworkController extends BaseController {

    @Autowired
    private ITeacherHomeworkService teacherHomeworkService;

    @GetMapping("/query")
    public TableDataInfo queryAllSatisfiedHomework(SpmHomework spmHomework){
        return getDataTable(teacherHomeworkService.selectAllSatisfiedHomework(spmHomework));
    }

    @GetMapping("/{id}")
    public AjaxResult queryDetailedHomework(@PathVariable("id") String id){
        return success(teacherHomeworkService.selectDetailSpmHomework(id));
    }

    @PostMapping("/add")
    // 前端字段{homeworkBody: SpmHomework, homeworkAppendix: MultipartFile}
    public AjaxResult insertIntoSpmHomework(@RequestParam("homeworkBody") String spmHomeworkString, @RequestParam("homeworkAppendix") MultipartFile file) throws IOException {
        System.out.println(spmHomeworkString);
        SpmHomework spmHomework = JSON.parseObject(spmHomeworkString, SpmHomework.class);
        String[] uploadPath = FileStorageHelper.generateUploadPath(spmHomework.getTitle(), file.getOriginalFilename(), "homework-storage/student-upload");
        file.transferTo(new File(uploadPath[0]));
        spmHomework.setAppendixLink(uploadPath[1]);
        return toAjax(teacherHomeworkService.insetIntoSpmHomeworkNewRecord(spmHomework));
    }

    @GetMapping("/select-student/{courseId}")
    public TableDataInfo querySelfCourseStudentSelect(@PathVariable("courseId") String courseId){
        return getDataTable(teacherHomeworkService.selectStudentByCourseId(courseId));
    }
}
