package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionVO;


public interface PromotionAdService {

    //分页显示广告信息
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionVO promotionVO);

    public void savePromotionAd(PromotionAd promotionAd);

    public void updatePromotionAd(PromotionAd promotionAd);

    public PromotionAd findPromotionAdById(int id);

    public void updatePromotionAdStatus(int id,int status);


}
