package com.easy.zadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.zadmin.pojo.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author sanye
 * @Date 2023/8/19 19:37
 * @Version 1.0
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 只需要根据用户id去查询到其所对应的权限信息即可。
     *
     *所以我们可以先定义个mapper，其中提供一个方法可以根据userid查询权限信息。
     * @param id  用户id
     * @return  得到权限集合
     */
    List<String> selectPermsByUserId(Long id);
}
