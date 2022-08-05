package com.lagou.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionVO;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    public PromotionAdMapper promotionMapper;

    @Override
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionVO promotionVO) {

        PageHelper.startPage(promotionVO.getCurrentPage(),promotionVO.getPageSize());
        List<PromotionAd> allAdByPage = promotionMapper.findAllPromotionAdByPage();
        PageInfo<PromotionAd> pageInfo = new PageInfo<>(allAdByPage);

        return pageInfo;
    }

    @Override
    public void savePromotionAd(PromotionAd promotionAd) {
        promotionMapper.savePromotionAd(promotionAd);
    }

    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {
        promotionMapper.updatePromotionAd(promotionAd);

    }

    @Override
    public PromotionAd findPromotionAdById(int id) {
        PromotionAd promotionAdById = promotionMapper.findPromotionAdById(id);
        return promotionAdById;
    }

    @Override
    public void updatePromotionAdStatus(int id,int status) {
        PromotionAd promotionAd = new PromotionAd();

        promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAd.setUpdateTime(new Date());

        promotionMapper.updatePromotionAdStatus(promotionAd);
    }
}
