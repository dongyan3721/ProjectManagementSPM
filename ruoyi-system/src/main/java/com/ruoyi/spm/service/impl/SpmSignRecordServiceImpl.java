package com.ruoyi.spm.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.spm.mapper.SpmSignRecordMapper;
import com.ruoyi.spm.domain.SpmSignRecord;
import com.ruoyi.spm.service.ISpmSignRecordService;

/**
 * 签到记录Service业务层处理
 * 
 * @author Antilles
 * @date 2023-12-24
 */
@Service
public class SpmSignRecordServiceImpl implements ISpmSignRecordService 
{
    @Autowired
    private SpmSignRecordMapper spmSignRecordMapper;

    /**
     * 查询签到记录
     * 
     * @param id 签到记录主键
     * @return 签到记录
     */
    @Override
    public SpmSignRecord selectSpmSignRecordById(String id)
    {
        return spmSignRecordMapper.selectSpmSignRecordById(id);
    }

    /**
     * 查询签到记录列表
     * 
     * @param spmSignRecord 签到记录
     * @return 签到记录
     */
    @Override
    public List<SpmSignRecord> selectSpmSignRecordList(SpmSignRecord spmSignRecord)
    {
        return spmSignRecordMapper.selectSpmSignRecordList(spmSignRecord);
    }

    /**
     * 新增签到记录
     * 
     * @param spmSignRecord 签到记录
     * @return 结果
     */
    @Override
    public int insertSpmSignRecord(SpmSignRecord spmSignRecord)
    {
        spmSignRecord.setCreateTime(DateUtils.getNowDate());
        return spmSignRecordMapper.insertSpmSignRecord(spmSignRecord);
    }

    /**
     * 修改签到记录
     * 
     * @param spmSignRecord 签到记录
     * @return 结果
     */
    @Override
    public int updateSpmSignRecord(SpmSignRecord spmSignRecord)
    {
        spmSignRecord.setUpdateTime(DateUtils.getNowDate());
        return spmSignRecordMapper.updateSpmSignRecord(spmSignRecord);
    }

    /**
     * 批量删除签到记录
     * 
     * @param ids 需要删除的签到记录主键
     * @return 结果
     */
    @Override
    public int deleteSpmSignRecordByIds(String[] ids)
    {
        return spmSignRecordMapper.deleteSpmSignRecordByIds(ids);
    }

    /**
     * 删除签到记录信息
     * 
     * @param id 签到记录主键
     * @return 结果
     */
    @Override
    public int deleteSpmSignRecordById(String id)
    {
        return spmSignRecordMapper.deleteSpmSignRecordById(id);
    }
}
