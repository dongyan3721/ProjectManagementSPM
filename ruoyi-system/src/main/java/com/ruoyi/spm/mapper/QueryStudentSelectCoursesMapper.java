package com.ruoyi.spm.mapper;

import java.util.List;

public interface QueryStudentSelectCoursesMapper {
    public List<String> getSelectedCoursesIds(String identification);

    // 根据课程id查选了课的学生的学号
    public List<String> selectStudentIdByCourseId(String courseId);
}
