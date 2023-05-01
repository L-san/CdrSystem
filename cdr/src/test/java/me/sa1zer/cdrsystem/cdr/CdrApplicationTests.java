package me.sa1zer.cdrsystem.cdr;

import me.sa1zer.cdrsystem.cdr.utils.CdrGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CdrApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void t(){
        CdrGenerator.generate();
    }

}
