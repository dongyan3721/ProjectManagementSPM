package com.ruoyi.spm.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.spm.domain.SpmSign;
import com.ruoyi.spm.domain.SpmSignRecord;
import com.ruoyi.spm.service.ISpmSignRecordService;
import com.ruoyi.spm.service.ISpmSignService;
import com.ruoyi.spm.service.ISpmStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spm/student-sign")
public class SpmStudentSignController extends BaseController {

    @Autowired
    private ISpmSignService spmSignService;

    @Autowired
    private ISpmSignRecordService spmSignRecordService;

    // 注进来用学生学号查学生姓名
    @Autowired
    private ISpmStudentService spmStudentService;

    /**
     * 学生查询本课所有签到
     * @param spmSign 只带courseId的SpmSign类
     */
    @GetMapping("/query/sign")
    public TableDataInfo queryThisCourseAllSign(SpmSign spmSign){
        return getDataTable(spmSignService.selectSpmSignList(spmSign));
    }

    /**
     * 学生查本次签到是否签上
     * @param spmSignRecord 传回来的时候只带signId，手动用SecurityUtil加上账户
     * 前台：如果列表长度为0，就是没签上，其他都是签上了，懒得判断懒了
     */
    @GetMapping("/query/sign-record")
    public TableDataInfo queryIfSigned(SpmSignRecord spmSignRecord){
        spmSignRecord.setStudentId(SecurityUtils.getUsername());
        return getDataTable(spmSignRecordService.selectSpmSignRecordList(spmSignRecord));
    }

    /**
     * 学生签到
     * @param spmSignRecord 前台封装好传递来的SpmSignRecord对象，带有时间信息、
     * 如果前台封装的时间比允许的大，返回签到失败
     * 前台不封装学生信息，这里手动用SecurityUtil加上
     */
    @PostMapping("add/sign-record")
    public AjaxResult studentAddNewSignRecord(SpmSignRecord spmSignRecord){
        SpmSign sign = spmSignService.selectSpmSignById(spmSignRecord.getSignId());
        // 签到时间在截止时间之前
        if(sign.getEndTime().compareTo(spmSignRecord.getSignTime())>0){
            String studentId = SecurityUtils.getUsername();
            spmSignRecord.setStudentId(studentId);
            spmSignRecord.setStudentName(spmStudentService.selectSpmStudentById(studentId).getName());
            return toAjax(spmSignRecordService.insertSpmSignRecord(spmSignRecord));
        }else{
            return error("签到已截止！");
        }
    }
}
