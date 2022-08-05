package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;

import java.util.List;

public interface CourseMapper {

    //多条件课程列表查询
    public List<Course> findCourseByCondition(CourseVO coursevo);

    //保存课程信息
    public int saveCourse(Course course);

    //保存讲师信息
    public void saveTeacher(Teacher teacher);

    //回显课程信息
    public CourseVO findCourseById(int id);

    //修改课程信息
    public void updateCourse(Course course);

    //修改教室信息
    public void updateTeacher(Teacher teacher);

    //修改课程状态
    public void updateCourseStatus(Course course);

    //


}
