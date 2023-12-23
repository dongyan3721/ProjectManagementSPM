package com.ruoyi.spm.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.spm.domain.SpmHomework;
import com.ruoyi.spm.domain.SpmHomeworkSubmit;
import com.ruoyi.spm.service.ISpmHomeworkSubmitService;
import com.ruoyi.spm.service.impl.SpmHomeworkSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spm/homework-submit")
public class SpmHomeworkSubmitController extends BaseController {

    @Autowired
    private ISpmHomeworkSubmitService spmHomeworkSubmitService;

    @GetMapping("/query")
    public TableDataInfo queryAllSatisfiedRecord(SpmHomeworkSubmit spmHomeworkSubmit){
        return getDataTable(spmHomeworkSubmitService.selectSpmHomeworkSubmitList(spmHomeworkSubmit));
    }

    @PostMapping("/add")
    public AjaxResult insertIntoHomeworkNewRecord(SpmHomeworkSubmit spmHomeworkSubmit){
        return toAjax(spmHomeworkSubmitService.insertSpmHomeworkSubmit(spmHomeworkSubmit));
    }
}
