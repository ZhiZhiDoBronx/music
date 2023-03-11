package com.zhizhidobronx.music.dao;
/*
 * 管理员dao
 */

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminMapper {
    /*
    * 验证密码是否正确
    */
    public int verifyPassword(String username, String password);
}
