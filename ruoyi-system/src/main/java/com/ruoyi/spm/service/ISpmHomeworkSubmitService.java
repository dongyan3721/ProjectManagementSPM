package com.ruoyi.spm.service;

import com.ruoyi.spm.domain.SpmHomeworkSubmit;

import java.util.List;

public interface ISpmHomeworkSubmitService {
    /**
     * 查询作业提交列表
     *
     * @param spmHomeworkSubmit 作业提交
     * @return 作业提交集合
     */
    public List<SpmHomeworkSubmit> selectSpmHomeworkSubmitList(SpmHomeworkSubmit spmHomeworkSubmit);

    /**
     * 新增作业提交
     *
     * @param spmHomeworkSubmit 作业提交
     * @return 结果
     */
    public int insertSpmHomeworkSubmit(SpmHomeworkSubmit spmHomeworkSubmit);
}
