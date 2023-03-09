package com.zhizhidobronx.music.service;


/*
* 管理员service接口
* */

public interface AdminService {
    /**
    * @Description: 验证密码是否正确
    * @Param: [username, password]
    * @return: boolean
    * @Author: zhizhi
    * @Date: 2023/2/25 19:26
    * @throws:
    * @Other:

    */
    public boolean verifyPassword(String username, String password);
}
