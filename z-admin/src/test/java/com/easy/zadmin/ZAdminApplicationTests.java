package com.easy.zadmin;

import com.easy.zadmin.mapper.UserMapper;
import com.easy.zadmin.pojo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ZAdminApplicationTests {

    @Test
    void contextLoads() {
    }
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Test
//    public void testUserMapper(){
//        List<User> users = userMapper.selectList(null);
//        System.out.println(users);
//    }

}
