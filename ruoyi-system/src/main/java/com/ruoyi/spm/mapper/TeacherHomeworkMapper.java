package com.ruoyi.spm.mapper;

import com.ruoyi.spm.domain.SpmHomework;

import java.util.List;

public interface TeacherHomeworkMapper {
    public List<SpmHomework> selectAllSatisfiedHomework(SpmHomework spmHomework);

    public SpmHomework selectDetailSpmHomework(String id);

    public int insetIntoSpmHomeworkNewRecord(SpmHomework spmHomework);
}
