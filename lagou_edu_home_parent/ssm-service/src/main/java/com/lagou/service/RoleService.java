package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVO;

import java.util.List;

public interface RoleService {

    //角色列表显示&条件查询
    public List<Role> findAllRole(Role role);

    public void saveRole(Role role);

    public void updateRole(Role role);

    //根据角色查询关联菜单
    public List<Integer> findMenuByRoleId(Integer roleId);

    //为角色分配菜单
    public void roleContextMenu(RoleMenuVO roleMenuVO);

    //删除角色
    public void deleteRole(Integer id);

}
