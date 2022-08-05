package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVO;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){

        List<Role> allRole = roleService.findAllRole(role);
        ResponseResult result = new ResponseResult(true, 200, "角色显示成功", allRole);
        return result;

    }

    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role){

        if(role.getId() == null){
            roleService.saveRole(role);
            ResponseResult result = new ResponseResult(true, 200, "新增角色成功", null);
            return result;
        }else {
            roleService.updateRole(role);
            ResponseResult result = new ResponseResult(true, 200, "修改角色成功", null);
            return result;
        }
    }

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid(){
        List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

        Map<String,Object> map = new HashMap<>();
        map.put("parentMenuList",subMenuListByPid);

        ResponseResult result = new ResponseResult(true, 200, "显示所有菜单成功", map);
        return result;
    }

    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(@RequestParam Integer roleId){

        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);
        ResponseResult result = new ResponseResult(true, 200, "显示关联菜单成功", menuByRoleId);

        return result;

    }


    @RequestMapping("/RoleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVO roleMenuVO){

        roleService.roleContextMenu(roleMenuVO);
        ResponseResult result = new ResponseResult(true, 200, "分配菜单成功", null);
        return result;

    }

    @RequestMapping("/deleteRole")
    public ResponseResult deleteRoleMenu(Integer id){

        roleService.deleteRole(id);
        ResponseResult result = new ResponseResult(true, 200, "删除角色成功", null);
        return result;

    }





}
