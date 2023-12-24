package com.ruoyi.spm.mapper;

import java.util.List;
import com.ruoyi.spm.domain.SpmSignRecord;

/**
 * 签到记录Mapper接口
 * 
 * @author Antilles
 * @date 2023-12-24
 */
public interface SpmSignRecordMapper 
{
    /**
     * 查询签到记录
     * 
     * @param id 签到记录主键
     * @return 签到记录
     */
    public SpmSignRecord selectSpmSignRecordById(String id);

    /**
     * 查询签到记录列表
     * 
     * @param spmSignRecord 签到记录
     * @return 签到记录集合
     */
    public List<SpmSignRecord> selectSpmSignRecordList(SpmSignRecord spmSignRecord);

    /**
     * 新增签到记录
     * 
     * @param spmSignRecord 签到记录
     * @return 结果
     */
    public int insertSpmSignRecord(SpmSignRecord spmSignRecord);

    /**
     * 修改签到记录
     * 
     * @param spmSignRecord 签到记录
     * @return 结果
     */
    public int updateSpmSignRecord(SpmSignRecord spmSignRecord);

    /**
     * 删除签到记录
     * 
     * @param id 签到记录主键
     * @return 结果
     */
    public int deleteSpmSignRecordById(String id);

    /**
     * 批量删除签到记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSpmSignRecordByIds(String[] ids);
}
