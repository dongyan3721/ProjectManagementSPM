package com.ruoyi.spm.service;

import java.util.List;
import com.ruoyi.spm.domain.SpmSign;

/**
 * 签到Service接口
 * 
 * @author Antilles
 * @date 2023-12-24
 */
public interface ISpmSignService 
{
    /**
     * 查询签到
     * 
     * @param id 签到主键
     * @return 签到
     */
    public SpmSign selectSpmSignById(String id);

    /**
     * 查询签到列表
     * 
     * @param spmSign 签到
     * @return 签到集合
     */
    public List<SpmSign> selectSpmSignList(SpmSign spmSign);

    /**
     * 新增签到
     * 
     * @param spmSign 签到
     * @return 结果
     */
    public int insertSpmSign(SpmSign spmSign);

    /**
     * 修改签到
     * 
     * @param spmSign 签到
     * @return 结果
     */
    public int updateSpmSign(SpmSign spmSign);

    /**
     * 批量删除签到
     * 
     * @param ids 需要删除的签到主键集合
     * @return 结果
     */
    public int deleteSpmSignByIds(String[] ids);

    /**
     * 删除签到信息
     * 
     * @param id 签到主键
     * @return 结果
     */
    public int deleteSpmSignById(String id);
}
