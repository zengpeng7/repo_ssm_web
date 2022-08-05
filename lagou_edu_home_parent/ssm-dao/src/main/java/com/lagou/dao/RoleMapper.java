package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {

    //角色列表显示&条件查询
    public List<Role> findAllRole(Role role);

    //添加角色
    public void saveRole(Role role);

    //修改角色
    public void updateRole(Role role);

    //根据角色ID查询关联菜单
    public List<Integer> findMenuByRoleId(Integer roleId);

    /*
     * 为角色分配菜单
     * 清空中间表的关联关系
     * */
    public void deleteRoleContextMenu(int roleid);

    //为角色分配菜单
    public void roleContextMenu(Role_menu_relation role_menu_relation);

    //删除角色
    public void deleteRole(Integer id);

}
