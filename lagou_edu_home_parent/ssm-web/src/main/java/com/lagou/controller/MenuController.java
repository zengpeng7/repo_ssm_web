package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenuList(){

        try {
            List<Menu> allMenu = menuService.findAllMenu();
            ResponseResult result = new ResponseResult(true, 200, "显示所有菜单成功", allMenu);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(@RequestParam int id){

        if(id == -1){
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            Map<String,Object> map =new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",subMenuListByPid);

            ResponseResult result = new ResponseResult(true, 200, "回显一级菜单成功", map);
            return result;
        }else {
            Menu menuById = menuService.findMenuById(id);

            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            Map<String,Object> map =new HashMap<>();
            map.put("menuInfo",menuById);
            map.put("parentMenuList",subMenuListByPid);

            ResponseResult result = new ResponseResult(true, 200, "回显修改信息", map);
            return result;

        }

    }

    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu){

        if(menu.getId()==null){
            menuService.saveMenu(menu);
            ResponseResult result = new ResponseResult(true, 200, "新增菜单成功", null);
            return result;
        }else {
            menuService.updateMenu(menu);
            ResponseResult result = new ResponseResult(true, 200, "修改菜单成功", null);
            return result;
        }

    }

}
