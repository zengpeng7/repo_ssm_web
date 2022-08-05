package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {


    @Autowired
    private PromotionAdService promotionAdService;

    @RequestMapping("/findAllPromotionAd")
    public ResponseResult findAllAdByPage(PromotionVO promotionVO){

        PageInfo<PromotionAd> allAdByPage = promotionAdService.findAllPromotionAdByPage(promotionVO);

        ResponseResult result = new ResponseResult(true, 200, "广告分页成功", allAdByPage);
        return result;
    }

    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileupload(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        try {
            if (file.isEmpty()) {
                throw new RuntimeException();
            }

            //获取项目部署路径
            String realPath=request.getServletContext().getRealPath("/");
            String webappsPath = realPath.substring(0,realPath.indexOf("ssm_web"));

            //获取原文件名
            String fileName = file.getOriginalFilename();

            //新文件名
            String newFileName = System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."));

            //上传文件
            String uploadPath = webappsPath+"upload\\";
            File filePath=new File(uploadPath,newFileName);

            //如果目录不存在就创建目录
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
                System.out.println("创建目录: " + filePath);
            }
            file.transferTo(filePath);
            //6.将文件名和文件路径返回
            Map<String, String> map = new HashMap<>();
            map.put("fileName", newFileName);
            map.put("filePath", "http://localhost:8081/upload/" + newFileName);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){


            if(promotionAd.getId()==null){
                Date date = new Date();
                promotionAd.setCreateTime(date);
                promotionAd.setUpdateTime(date);
                promotionAdService.savePromotionAd(promotionAd);
                ResponseResult result = new ResponseResult(true, 200, "新增广告成功", null);
                return result;

            }else {
                Date date = new Date();
                promotionAd.setUpdateTime(date);
                promotionAdService.updatePromotionAd(promotionAd);
                ResponseResult result = new ResponseResult(true, 200, "修改广告成功", null);
                return result;
            }

    }

    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionById(@RequestParam int id){

        PromotionAd promotionAdById = promotionAdService.findPromotionAdById(id);
        ResponseResult result = new ResponseResult(true, 200, "回显广告信息成功", promotionAdById);
        return result;

    }

    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionStatus(@RequestParam int id, @RequestParam int status){

        if(status == 1){
            promotionAdService.updatePromotionAdStatus(id,status);
        }else {
            promotionAdService.updatePromotionAdStatus(id,0);
        }

        Map<String,Integer> map=new HashMap<>();
        map.put("status",status);

        ResponseResult result = new ResponseResult(true, 200, "修改广告状态成功", map);
        return result;

    }

}
