package com.ruoyi.spm.mapper;

import java.util.List;
import com.ruoyi.spm.domain.SpmHomeworkSubmit;

/**
 * 作业提交Mapper接口
 * 
 * @author ruoyi
 * @date 2023-12-23
 */
public interface SpmHomeworkSubmitMapper 
{
    /**
     * 查询作业提交
     * 
     * @param id 作业提交主键
     * @return 作业提交
     */
    public SpmHomeworkSubmit selectSpmHomeworkSubmitById(String id);

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

    /**
     * 修改作业提交
     * 
     * @param spmHomeworkSubmit 作业提交
     * @return 结果
     */
    public int updateSpmHomeworkSubmit(SpmHomeworkSubmit spmHomeworkSubmit);

    /**
     * 删除作业提交
     * 
     * @param id 作业提交主键
     * @return 结果
     */
    public int deleteSpmHomeworkSubmitById(String id);

    /**
     * 批量删除作业提交
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSpmHomeworkSubmitByIds(String[] ids);
}
