package com.lagou.service;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuService {

    public List<Menu> findSubMenuListByPid(int pid);

    public List<Menu> findAllMenu();

    //回显菜单信息
    public Menu findMenuById(int id);

    //新增菜单
    public void saveMenu(Menu menu);

    //修改菜单
    public void updateMenu(Menu menu);


}
