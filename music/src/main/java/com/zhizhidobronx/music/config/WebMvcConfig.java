package com.zhizhidobronx.music.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @program: music
 * @description: 解决跨域问题
 * @author: ZhiZhio_O
 * @create: 2023-02-25 15:39
 * @other:
 **/
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//允许哪些目录进行跨域访问
                .allowedOrigins("*")//允许哪些网站进行跨域
                .allowedMethods("*")//允许哪些方法进行跨域
                .allowCredentials(true);//设置访问验证
    }

}
