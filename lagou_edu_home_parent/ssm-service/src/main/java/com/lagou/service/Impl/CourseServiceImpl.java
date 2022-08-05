package com.lagou.service.Impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findCourseByCondition(CourseVO coursevo) {

        List<Course> courseList = courseMapper.findCourseByCondition(coursevo);

        return courseList;
    }

    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) {
        Course course = new Course();
        BeanUtils.copyProperties(courseVO,course);

        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);

        courseMapper.saveCourse(course);

        int id = course.getId();

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(courseVO,teacher);


        teacher.setCourseId(id);
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);

        courseMapper.saveTeacher(teacher);
    }

    @Override
    public CourseVO findCourseById(Integer id) {

        return courseMapper.findCourseById(id);
    }

    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) {
        Course course=new Course();
        BeanUtils.copyProperties(courseVO, course);

        course.setUpdateTime(new Date());

        courseMapper.updateCourse(course);

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(courseVO, teacher);

        teacher.setCourseId(course.getId());
        teacher.setUpdateTime(new Date());

        courseMapper.updateTeacher(teacher);

    }

    @Override
    public void updateCourseStatus(int id, int status) {

        try {
            Course course = new Course();
            course.setId(id);
            course.setStatus(status);
            course.setUpdateTime(new Date());

            courseMapper.updateCourseStatus(course);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
