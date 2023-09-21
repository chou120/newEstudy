package com.easy.zadmin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.easy.zadmin.mapper.MenuMapper;
import com.easy.zadmin.mapper.UserMapper;
import com.easy.zadmin.pojo.entity.User;
import com.easy.zadmin.pojo.dto.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;
    private Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LOGGER.error("email:"+email);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail,email);
        User user = userMapper.selectOne(wrapper);
        LOGGER.error("user："+user);

        //如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }
        //TODO 根据用户查询权限信息 添加到LoginUser中
        List<String> permissionKeyList = menuMapper.selectPermsByUserId(user.getId());

        LOGGER.info("permissionKeyList权限列表："+permissionKeyList);
        if(permissionKeyList.size()==0){
            LOGGER.info("当前用户无任何权限");
            return null;
        }

        return new LoginUser(user,permissionKeyList);
    }
}
