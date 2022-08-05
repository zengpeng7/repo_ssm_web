package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;

import java.util.List;
import java.util.Map;

public interface CourseService {

    public List<Course> findCourseByCondition(CourseVO coursevo);

    public void saveCourseOrTeacher(CourseVO courseVO);

    public CourseVO findCourseById(Integer id);

    public void updateCourseOrTeacher(CourseVO courseVO);

    public void updateCourseStatus(int id,int status);

}
