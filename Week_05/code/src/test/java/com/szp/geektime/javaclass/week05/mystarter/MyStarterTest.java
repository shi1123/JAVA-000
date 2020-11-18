package com.szp.geektime.javaclass.week05.mystarter;

import com.szp.geektime.javaclass.week05.mystarter.configuration.MySchoolAutoConfiguration;
import com.szp.geektime.javaclass.week05.mystarter.pojo.MySchool;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MySchoolAutoConfiguration.class)
public class MyStarterTest {
    @Autowired
    MySchool mySchool;

    @Test
    public void test() {
        System.out.println(mySchool.toString());
    }
}
