package com.ruoyi.spm.service.impl;

import com.ruoyi.spm.domain.SpmHomeworkScore;
import com.ruoyi.spm.mapper.SpmHomeworkScoreMapper;
import com.ruoyi.spm.service.ISpmHomeworkScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpmHomeworkScoreService implements ISpmHomeworkScoreService {

    @Autowired
    private SpmHomeworkScoreMapper spmHomeworkScoreMapper;

    @Override
    public SpmHomeworkScore selectSpmHomeworkScoreById(String id) {
        return spmHomeworkScoreMapper.selectSpmHomeworkScoreById(id);
    }

    @Override
    public List<SpmHomeworkScore> selectSpmHomeworkScoreList(SpmHomeworkScore spmHomeworkScore) {
        return spmHomeworkScoreMapper.selectSpmHomeworkScoreList(spmHomeworkScore);
    }

    @Override
    public int insertSpmHomeworkScore(SpmHomeworkScore spmHomeworkScore) {
        return spmHomeworkScoreMapper.insertSpmHomeworkScore(spmHomeworkScore);
    }
}
