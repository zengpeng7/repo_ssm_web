package com.lagou.dao;

import com.lagou.domain.Menu;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TestMapper {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void TestExample(){


        List<Integer> list = new ArrayList<>();
        list.add(2);
        List<Menu> parentMenuByRoleId = userMapper.findParentMenuByRoleId(list);
        for (Menu menu : parentMenuByRoleId) {
            System.out.println(menu);

        }
    }

}
