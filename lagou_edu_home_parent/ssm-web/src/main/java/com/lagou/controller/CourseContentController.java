package com.lagou.controller;


import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;



    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(@RequestParam int courseId){
        try {
            List<CourseSection> sectionList = courseContentService.findSectionAndLessonByCourseId(courseId);

            ResponseResult result = new ResponseResult(true, 200, "显示章节与课时成功", sectionList);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(@RequestParam int courseId){

        Course course = courseContentService.findCourseByCourseId(courseId);

        //String name = course.getCourseName();

        ResponseResult result = new ResponseResult(true, 200, "显示成功",course);

        return result;
    }

    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){

        if(courseSection.getId()==null){
            courseContentService.saveSection(courseSection);

            ResponseResult result = new ResponseResult(true, 200, "新建章节成功", null);

            return result;
        }else {
            courseContentService.updateSection(courseSection);

            ResponseResult result = new ResponseResult(true, 200, "修改章节成功", null);

            return result;
        }

    }

   @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(@RequestParam int id, @RequestParam int status){

        courseContentService.updateSectionStatus(id,status);

        Map<String,Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "修改章节状态成功", map);
        return result;
    }

    @RequestMapping("/saveLesson")
    public ResponseResult saveLessons(@RequestBody CourseLesson courseLesson){

        courseContentService.saveLesson(courseLesson);

        ResponseResult result = new ResponseResult(true, 200, "新增课时成功", null);
        return result;
    }


}
