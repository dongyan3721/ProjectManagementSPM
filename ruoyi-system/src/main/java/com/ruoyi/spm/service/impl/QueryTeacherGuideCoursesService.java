package com.ruoyi.spm.service.impl;

import com.ruoyi.spm.domain.SpmCourses;
import com.ruoyi.spm.mapper.QueryTeacherGuideCoursesMapper;
import com.ruoyi.spm.mapper.SpmCoursesMapper;
import com.ruoyi.spm.service.IQueryTeacherGuideCoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Santa Antilles
 * @Date 2023/12/17-16:16
 */
@Service
public class QueryTeacherGuideCoursesService implements IQueryTeacherGuideCoursesService {

    @Autowired
    private QueryTeacherGuideCoursesMapper queryTeacherGuideCoursesMapper;

    @Autowired
    private SpmCoursesMapper spmCoursesMapper;
    @Override
    public List<SpmCourses> getGuideCourses(String identification) {
        ArrayList<SpmCourses> ret = new ArrayList<>();
        for (String guideCoursesId : queryTeacherGuideCoursesMapper.getGuideCoursesIds(identification)) {
            ret.add(spmCoursesMapper.selectSpmCoursesById(guideCoursesId));
        }
        return ret;
    }
}
