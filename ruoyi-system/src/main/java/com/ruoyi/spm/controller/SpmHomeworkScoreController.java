package com.ruoyi.spm.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.spm.domain.SpmHomeworkScore;
import com.ruoyi.spm.service.ISpmHomeworkScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spm/homework-score")
public class SpmHomeworkScoreController extends BaseController {

    @Autowired
    private ISpmHomeworkScoreService spmHomeworkScoreService;

    @GetMapping("query")
    public TableDataInfo queryAllSatisfiedRecord(SpmHomeworkScore spmHomeworkScore){
        return getDataTable(spmHomeworkScoreService.selectSpmHomeworkScoreList(spmHomeworkScore));
    }

    @GetMapping("{id}")
    public AjaxResult queryRecordBuId(@PathVariable("id") String id){
        return success(spmHomeworkScoreService.selectSpmHomeworkScoreById(id));
    }

    @PostMapping("/add")
    public AjaxResult insertIntoHomeworkNewScore(@RequestBody SpmHomeworkScore spmHomeworkScore){
        System.out.println(spmHomeworkScore);
        return toAjax(spmHomeworkScoreService.insertSpmHomeworkScore(spmHomeworkScore));
    }
}
