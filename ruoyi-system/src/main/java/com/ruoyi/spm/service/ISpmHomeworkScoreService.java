package com.ruoyi.spm.service;

import com.ruoyi.spm.domain.SpmHomeworkScore;

import java.util.List;

public interface ISpmHomeworkScoreService {
    /**
     * 查询作业得分
     *
     * @param id 作业得分主键
     * @return 作业得分
     */
    public SpmHomeworkScore selectSpmHomeworkScoreById(String id);

    /**
     * 查询作业得分列表
     *
     * @param spmHomeworkScore 作业得分
     * @return 作业得分集合
     */
    public List<SpmHomeworkScore> selectSpmHomeworkScoreList(SpmHomeworkScore spmHomeworkScore);

    /**
     * 新增作业得分
     *
     * @param spmHomeworkScore 作业得分
     * @return 结果
     */
    public int insertSpmHomeworkScore(SpmHomeworkScore spmHomeworkScore);
}
