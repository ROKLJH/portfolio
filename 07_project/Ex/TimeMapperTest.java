package com.KoreaItAcdemy.OurNotion.mapper;

import com.KoreaItAcdemy.OurNotion.util.MyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class TimeMapperTest {

    @Autowired
    private TimeMapper mapper;

    @Test
    public void testGetTime1() {
        log.info("------------------------------");
        log.info("Current Time 1 : " + MyUtil.BOLD + mapper.getTime1() + MyUtil.END);
        log.info("------------------------------");
    }

    @Test
    public void testGetTime2() {
        log.info("------------------------------");
        log.info("Current Time 2 : " + MyUtil.BOLD + mapper.getTime2() + MyUtil.END);
        log.info("------------------------------");
    }
}
