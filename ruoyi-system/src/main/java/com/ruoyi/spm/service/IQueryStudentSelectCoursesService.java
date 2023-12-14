package com.ruoyi.spm.service;

import com.ruoyi.spm.domain.SpmCourses;

import java.util.List;

public interface IQueryStudentSelectCoursesService {
    public List<SpmCourses> getSelectedCourses(String identification);
}
