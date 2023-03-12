package com.dobronx.music;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class MusicApplicationTests {

    @Test
    void test() {
        File file = new File("song/1678545684689Bones - Imagine Dragons.mp3");
        //file.delete();
        String[] fileName = file.list();
        return;
    }

}
