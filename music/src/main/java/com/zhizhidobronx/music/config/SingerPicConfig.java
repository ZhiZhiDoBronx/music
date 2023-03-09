package com.zhizhidobronx.music.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

/**
 * @program: music
 * @description: 定位歌手头像地址
 * @author: ZhiZhio_O
 * @create: 2023-03-02 09:19
 * @other:
 **/

@Configuration
public class SingerPicConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/img/singerPic/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                        +System.getProperty("file.separator")+"singerPic"+System.getProperty("file.separator")
        );
    }

}
