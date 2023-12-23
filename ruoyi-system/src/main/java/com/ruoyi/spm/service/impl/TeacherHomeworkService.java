package com.ruoyi.spm.service.impl;

import com.ruoyi.spm.domain.SpmHomework;
import com.ruoyi.spm.domain.SpmStudent;
import com.ruoyi.spm.mapper.QueryStudentSelectCoursesMapper;
import com.ruoyi.spm.mapper.SpmStudentMapper;
import com.ruoyi.spm.mapper.TeacherHomeworkMapper;
import com.ruoyi.spm.service.ITeacherHomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherHomeworkService implements ITeacherHomeworkService {

    @Autowired
    private TeacherHomeworkMapper teacherHomeworkMapper;

    @Autowired
    private QueryStudentSelectCoursesMapper queryStudentSelectCoursesMapper;

    @Autowired
    private SpmStudentMapper spmStudentMapper;

    @Override
    public List<SpmHomework> selectAllSatisfiedHomework(SpmHomework spmHomework) {
        return teacherHomeworkMapper.selectAllSatisfiedHomework(spmHomework);
    }

    @Override
    public SpmHomework selectDetailSpmHomework(String id) {
        return teacherHomeworkMapper.selectDetailSpmHomework(id);
    }

    @Override
    public int insetIntoSpmHomeworkNewRecord(SpmHomework spmHomework) {
        return teacherHomeworkMapper.insetIntoSpmHomeworkNewRecord(spmHomework);
    }

    @Override
    public List<SpmStudent> selectStudentByCourseId(String courseId) {
        ArrayList<SpmStudent> ret = new ArrayList<>();
        for (String studentId : queryStudentSelectCoursesMapper.selectStudentIdByCourseId(courseId)) {
            ret.add(spmStudentMapper.selectSpmStudentById(studentId));
        }
        return ret;
    }
}
