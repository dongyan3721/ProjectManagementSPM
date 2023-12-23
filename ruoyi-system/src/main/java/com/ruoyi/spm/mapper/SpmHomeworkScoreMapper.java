package com.ruoyi.spm.mapper;

import java.util.List;
import com.ruoyi.spm.domain.SpmHomeworkScore;

/**
 * 作业得分Mapper接口
 * 
 * @author ruoyi
 * @date 2023-12-24
 */
public interface SpmHomeworkScoreMapper 
{
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

    /**
     * 修改作业得分
     * 
     * @param spmHomeworkScore 作业得分
     * @return 结果
     */
    public int updateSpmHomeworkScore(SpmHomeworkScore spmHomeworkScore);

    /**
     * 删除作业得分
     * 
     * @param id 作业得分主键
     * @return 结果
     */
    public int deleteSpmHomeworkScoreById(String id);

    /**
     * 批量删除作业得分
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSpmHomeworkScoreByIds(String[] ids);
}
