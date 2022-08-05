package com.lagou.dao;

import com.lagou.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    //多条件查询及分页
    public List<User> findAllUserByPage(UserVO userVO);

    //修改用户状态
    public void updateUserStatus(@Param("id") int id, @Param("status") String status);

    //用户登录
    public User login(User user);

    /*public void updateUser(User user);*/

    //获取用户拥有的角色
    public List<Role> findUserRelationRoleById(int id);

    //删除用户角色所拥有的关系
    public void deleteUserContextRole(int userId);

    //新添加用户的角色信息
    public void userContextRole(User_Role_relation user_role_relation);


    //根据角色id,获取角色拥有的顶级菜单
    public List<Menu> findParentMenuByRoleId(List<Integer> rid);


    //根据顶级菜单id,获取子菜单
    public List<Menu> findSubMenuByPid(int pid);


    //获取用户拥有的资源权限信息
    public List<Resource> findResourceByRoleId(List<Integer> rid);


}
