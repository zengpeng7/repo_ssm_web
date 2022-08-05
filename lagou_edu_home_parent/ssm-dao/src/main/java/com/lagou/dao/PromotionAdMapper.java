package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    //分页查询广告信息
    public List<PromotionAd> findAllPromotionAdByPage();

    //新增广告
    public void savePromotionAd(PromotionAd promotionAd);

    //修改广告
    public void updatePromotionAd(PromotionAd promotionAd);

    //回显修改广告信息
    public PromotionAd findPromotionAdById(int id);

    //修改广告状态
    public void updatePromotionAdStatus(PromotionAd promotionAd);


}
