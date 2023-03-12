package com.zhizhidobronx.music;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.testng.annotations.Test;

import java.io.File;

@SpringBootApplication
@MapperScan("com.zhizhidobronx.music.dao")
public class MusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicApplication.class, args);
    }


    @Test
    void test() {
        File file = new File("song/1678545684689Bones - Imagine Dragons.mp3");
        //file.delete();
        String[] fileName = file.list();
        return;
    }

}
