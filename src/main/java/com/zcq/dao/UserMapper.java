package com.zcq.dao;

import com.zcq.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @description: 测试Mybatis
 * @author: congqi.zhao
 * @date: Created in 2021/9/8 21:13
 */
@Repository
public interface UserMapper {
    //@Insert("INSERT INTO user(id, name, pwd) VALUES (#{id}, #{name}, #{pwd})")
    Integer insert(User user);
}
