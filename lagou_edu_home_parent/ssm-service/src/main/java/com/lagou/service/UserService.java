package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;

import java.util.List;

public interface UserService {

    //多条件查询&分页
    public PageInfo<User> findAllUserByPage(UserVO userVO);

    //修改用户状态
    public void updateUserStatus(int id,String status);

    //用户登录
    public User login(User user);

    //获取用户拥有的角色
    public List<Role> findUserRelationRoleById(int id);

    //用户关联角色
    void userContextRole(UserVO userVO);

    //获取用户权限
    ResponseResult getUserPermissions(Integer id);

}
