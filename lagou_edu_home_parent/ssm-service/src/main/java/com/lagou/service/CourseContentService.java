package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {

    public List<CourseSection> findSectionAndLessonByCourseId(int courseId);

    //回显章节对应课程信息
    public Course findCourseByCourseId(int courseId);

    //新建章节信息
    public void saveSection(CourseSection courseSection);

    public void updateSection(CourseSection courseSection);

    public void updateSectionStatus(int id,int status);

    //新增课时信息
    public void saveLesson(CourseLesson courseLesson);
}
