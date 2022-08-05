package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;

import java.util.List;

public interface ResourceMapper {

    //分页&资源信息显示
    public List<Resource> findAllResource(ResourceVO resourceVO);

}
