package com.ruoyi.spm.service.impl;

import com.ruoyi.spm.domain.SpmHomeworkSubmit;
import com.ruoyi.spm.mapper.SpmHomeworkSubmitMapper;
import com.ruoyi.spm.service.ISpmHomeworkSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpmHomeworkSubmitService implements ISpmHomeworkSubmitService {

    @Autowired
    private SpmHomeworkSubmitMapper spmHomeworkSubmitMapper;

    @Override
    public List<SpmHomeworkSubmit> selectSpmHomeworkSubmitList(SpmHomeworkSubmit spmHomeworkSubmit) {
        return spmHomeworkSubmitMapper.selectSpmHomeworkSubmitList(spmHomeworkSubmit);
    }

    @Override
    public int insertSpmHomeworkSubmit(SpmHomeworkSubmit spmHomeworkSubmit) {
        return spmHomeworkSubmitMapper.insertSpmHomeworkSubmit(spmHomeworkSubmit);
    }
}
