package com.lagou.service;

import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {

    //获取广告位信息
    public List<PromotionSpace> findAllPromotionSpace();

    //新增广告位信息
    public void savePromotionSpace(PromotionSpace promotionSpace);

    //修改广告位信息
    public void updatePromotionSpace(PromotionSpace promotionSpace);

    //回显广告位名称
    public PromotionSpace findPromotionSpaceById(int id);

}
