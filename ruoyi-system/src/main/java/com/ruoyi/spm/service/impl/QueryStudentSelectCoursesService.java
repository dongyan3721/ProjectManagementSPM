package com.ruoyi.spm.service.impl;

import com.ruoyi.spm.domain.SpmCourses;
import com.ruoyi.spm.mapper.QueryStudentSelectCoursesMapper;
import com.ruoyi.spm.mapper.SpmCoursesMapper;
import com.ruoyi.spm.service.IQueryStudentSelectCoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Santa Antilles
 * @Date 2023/12/14-16:30
 */
@Service
public class QueryStudentSelectCoursesService implements IQueryStudentSelectCoursesService {

    @Autowired
    private QueryStudentSelectCoursesMapper queryStudentSelectCoursesMapper;

    @Autowired
    private SpmCoursesMapper spmCoursesMapper;


    @Override
    public List<SpmCourses> getSelectedCourses(String identification) {
        List<String> selectedCoursesIds = queryStudentSelectCoursesMapper.getSelectedCoursesIds(identification);
        ArrayList<SpmCourses> ret = new ArrayList<>();
        selectedCoursesIds.forEach(id->{
            ret.add(spmCoursesMapper.selectSpmCoursesById(id));
        });
        return ret;
    }
}
