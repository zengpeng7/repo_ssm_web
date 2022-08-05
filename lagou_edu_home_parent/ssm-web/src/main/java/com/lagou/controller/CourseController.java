package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/findAllCourse")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO coursevo) {

        List<Course> coureList = courseService.findCourseByCondition(coursevo);

        ResponseResult result = new ResponseResult(true, 200, "响应成功", coureList);

        return result;

    }

    /**
     * 图片上传接口
     */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file,
                                     HttpServletRequest request) {
        try {
            //1.判断文件是否为空
            if (file.isEmpty()) {
                throw new RuntimeException();
            }
            //2.获取项目部署路径
             // D:\apache-tomcat-8.5.56\webapps\ssm_web\
            String realPath = request.getServletContext().getRealPath("/");
            // D:\apache-tomcat-8.5.56\webapps\
            String webappsPath =
                    realPath.substring(0, realPath.indexOf("ssm_web"));
            //3.获取原文件名
            String fileName = file.getOriginalFilename();
            //4.新文件名
            String newFileName = System.currentTimeMillis() +
                    fileName.substring(fileName.lastIndexOf("."));
            //5.上传文件
            String uploadPath = webappsPath + "upload\\";
            File filePath = new File(uploadPath, newFileName);
           //如果目录不存在就创建目录
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
                System.out.println("创建目录: " + filePath);
            }
            file.transferTo(filePath);
           //6.将文件名和文件路径返回
            Map<String, String> map = new HashMap<>();
            map.put("fileName", newFileName);
            map.put("filePath", "http://localhost:8080/upload/" + newFileName);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourses(@RequestBody CourseVO courseVO){

       /* courseService.saveCourseOrTeacher(courseVO);

        ResponseResult result = new ResponseResult(true,200,"响应成功",null);

        return result;*/

       if(courseVO.getId() == null){
            courseService.saveCourseOrTeacher(courseVO);

            ResponseResult result = new ResponseResult(true,200,"响应成功",null);

            return result;

        }else {
            courseService.updateCourseOrTeacher(courseVO);

            ResponseResult result = new ResponseResult(true,200,"修改成功",null);

            return result;
        }


    }

    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(@RequestParam int id){
        try {
            CourseVO courseVO = courseService.findCourseById(id);

            ResponseResult result = new ResponseResult(true, 200, "回显成功", courseVO);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(@RequestParam int id ,@RequestParam int status){

        try {
            courseService.updateCourseStatus(id,status);

            Map<String,Integer> map = new HashMap<>();
            map.put("status",status);

            ResponseResult result = new ResponseResult(true, 200, "修改状态成功", map);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
