package com.lagou.service.Impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVO;
import com.lagou.domain.Role_menu_relation;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {

        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    @Override
    public void saveRole(Role role) {

        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        roleMapper.saveRole(role);
    }

    @Override
    public void updateRole(Role role) {

        role.setUpdatedTime(new Date());
        roleMapper.updateRole(role);
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {

        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleId);
        return menuByRoleId;
    }

    @Override
    public void roleContextMenu(RoleMenuVO roleMenuVO) {

        //1、删除中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVO.getRoleId());

        //2、为角色分配菜单
        for(Integer menuId:roleMenuVO.getMenuIdList()){
            Role_menu_relation role_menu_relation = new Role_menu_relation();

            role_menu_relation.setMenuId(menuId);
            role_menu_relation.setRoleId(roleMenuVO.getRoleId());
            role_menu_relation.setCreatedTime(new Date());
            role_menu_relation.setUpdatedTime(new Date());
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");

            roleMapper.roleContextMenu(role_menu_relation);

        }

    }

    @Override
    public void deleteRole(Integer id) {

        //删除中间表角色信息
        roleMapper.deleteRoleContextMenu(id);
        roleMapper.deleteRole(id);
    }
}
