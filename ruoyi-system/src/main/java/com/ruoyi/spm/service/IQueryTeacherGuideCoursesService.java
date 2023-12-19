package com.ruoyi.spm.service;

import com.ruoyi.spm.domain.SpmCourses;

import java.util.List;

public interface IQueryTeacherGuideCoursesService {
    public List<SpmCourses> getGuideCourses(String identification);
}
