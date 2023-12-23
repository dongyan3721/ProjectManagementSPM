package com.ruoyi.spm.service;

import com.ruoyi.spm.domain.SpmHomework;
import com.ruoyi.spm.domain.SpmStudent;

import java.util.List;

public interface ITeacherHomeworkService {
    public List<SpmHomework> selectAllSatisfiedHomework(SpmHomework spmHomework);

    public SpmHomework selectDetailSpmHomework(String id);

    public int insetIntoSpmHomeworkNewRecord(SpmHomework spmHomework);

    public List<SpmStudent> selectStudentByCourseId(String courseId);
}
