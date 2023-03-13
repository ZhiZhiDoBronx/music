package com.zhizhidobronx.music;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.testng.annotations.Test;

import java.io.File;

@SpringBootApplication
@MapperScan("com.zhizhidobronx.music.dao")
@Log4j2
public class MusicApplication {

    public static void main(String[] args) {
        LogFactory.useCustomLogging(StdOutImpl.class);
        SpringApplication.run(MusicApplication.class, args);
        log.info("springboot start!!!");
    }


    @Test
    void test() {
        File file = new File("song/1678545684689Bones - Imagine Dragons.mp3");
        //file.delete();
        String[] fileName = file.list();
        return;
    }

}
