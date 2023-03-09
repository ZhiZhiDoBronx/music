package com.zhizhidobronx.music.service.impl;

import com.zhizhidobronx.music.dao.AdminMapper;
import com.zhizhidobronx.music.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: music
 * @description: 登录验证实现
 * @author: ZhiZhio_O
 * @create: 2023-02-25 16:07
 * @other:
 **/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    /**
     * @Description: 验证密码是否正确
     * @Param: [username, password]
     * @return: boolean
     * @Author: zhizhi
     * @Date: 2023/2/25 19:26
     * @throws:
     * @Other:

     */
    public boolean verifyPassword(String username, String password) {
        return adminMapper.verifyPassword(username, password) > 0 ? true: false;
    }
}
