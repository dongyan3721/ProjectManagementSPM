package com.ruoyi.spm.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.spm.domain.SpmSign;
import com.ruoyi.spm.domain.SpmSignRecord;
import com.ruoyi.spm.service.ISpmSignRecordService;
import com.ruoyi.spm.service.ISpmSignService;
import com.ruoyi.spm.service.ITeacherHomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spm/teacher-sign")
public class SpmTeacherSignController extends BaseController {

    @Autowired
    private ISpmSignRecordService spmSignRecordService;

    @Autowired
    private ISpmSignService spmSignService;

    // 注入进来查有哪些学生选了这个课，这里也用到了
    @Autowired
    private ITeacherHomeworkService teacherHomeworkService;

    /**
     * 返回本课程一共发布过哪些签到
     * @param spmSign 参数
     */
    // 传参：只有courseId的SpmSign
    @GetMapping("/query/sign")
    public TableDataInfo querySatisfiedPublishedSign(SpmSign spmSign){
        return getDataTable(spmSignService.selectSpmSignList(spmSign));
    }

    /**
     * 返回有哪些学生在本次签到里面签上了
     * @param spmSignRecord 参数
     */
    // 传参：只有signId的SpmSignRecord
    @GetMapping("/query/sign-record")
    public TableDataInfo querySatisfiedSignRecord(SpmSignRecord spmSignRecord){
        return getDataTable(spmSignRecordService.selectSpmSignRecordList(spmSignRecord));
    }

    /**
     * 查本课有哪些学生选了
     * @param courseId 课程号
     */
    @GetMapping("/select-student/{courseId}")
    public TableDataInfo querySelfCourseStudentSelect(@PathVariable("courseId") String courseId){
        return getDataTable(teacherHomeworkService.selectStudentByCourseId(courseId));
    }

    /**
     * 某次签到详情
     * @param signId 签到id
     */
    @GetMapping("/sign-detail/{signId}")
    public AjaxResult queryDetailedSign(@PathVariable("signId") String signId){
        return success(spmSignService.selectSpmSignById(signId));
    }

    /**
     * 某学生签到记录的详情
     * @param signRecordId 签到记录id
     */
    @GetMapping("/sign-record-detail/{signRecordId}")
    public AjaxResult queryDetailedSignRecord(@PathVariable("signRecordId") String signRecordId){
        return success(spmSignRecordService.selectSpmSignRecordById(signRecordId));
    }

    /**
     * 老师发布签到
     * @param spmSign 记录
     */
    @PostMapping("/sign/add")
    public AjaxResult publishNewSign(SpmSign spmSign){
        return toAjax(spmSignService.insertSpmSign(spmSign));
    }

    /**
     * 老师手动给学生签到
     * @param spmSignRecord 记录
     */
    @PostMapping("/sign-record/add")
    public AjaxResult pushNewStudentSignRecord(SpmSignRecord spmSignRecord){
        return toAjax(spmSignRecordService.insertSpmSignRecord(spmSignRecord));
    }

    /**
     * 老师删除非法签到学生
     * @param id 签到记录id
     */
    @DeleteMapping("/sign-record/delete")
    public AjaxResult deleteFakeSignRecord(String[] id){
        return toAjax(spmSignRecordService.deleteSpmSignRecordByIds(id));
    }

}
