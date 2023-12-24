package com.ruoyi.spm.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.spm.mapper.SpmSignMapper;
import com.ruoyi.spm.domain.SpmSign;
import com.ruoyi.spm.service.ISpmSignService;

/**
 * 签到Service业务层处理
 * 
 * @author Antilles
 * @date 2023-12-24
 */
@Service
public class SpmSignServiceImpl implements ISpmSignService 
{
    @Autowired
    private SpmSignMapper spmSignMapper;

    /**
     * 查询签到
     * 
     * @param id 签到主键
     * @return 签到
     */
    @Override
    public SpmSign selectSpmSignById(String id)
    {
        return spmSignMapper.selectSpmSignById(id);
    }

    /**
     * 查询签到列表
     * 
     * @param spmSign 签到
     * @return 签到
     */
    @Override
    public List<SpmSign> selectSpmSignList(SpmSign spmSign)
    {
        return spmSignMapper.selectSpmSignList(spmSign);
    }

    /**
     * 新增签到
     * 
     * @param spmSign 签到
     * @return 结果
     */
    @Override
    public int insertSpmSign(SpmSign spmSign)
    {
        spmSign.setCreateTime(DateUtils.getNowDate());
        return spmSignMapper.insertSpmSign(spmSign);
    }

    /**
     * 修改签到
     * 
     * @param spmSign 签到
     * @return 结果
     */
    @Override
    public int updateSpmSign(SpmSign spmSign)
    {
        spmSign.setUpdateTime(DateUtils.getNowDate());
        return spmSignMapper.updateSpmSign(spmSign);
    }

    /**
     * 批量删除签到
     * 
     * @param ids 需要删除的签到主键
     * @return 结果
     */
    @Override
    public int deleteSpmSignByIds(String[] ids)
    {
        return spmSignMapper.deleteSpmSignByIds(ids);
    }

    /**
     * 删除签到信息
     * 
     * @param id 签到主键
     * @return 结果
     */
    @Override
    public int deleteSpmSignById(String id)
    {
        return spmSignMapper.deleteSpmSignById(id);
    }
}
