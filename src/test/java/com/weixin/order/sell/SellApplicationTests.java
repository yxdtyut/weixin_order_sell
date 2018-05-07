package com.weixin.order.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellApplicationTests {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void contextLoads() {
//		logger.trace("this is trace");
//		logger.debug("this is debug");
//		logger.info("this is info");
//		logger.warn("this is warn");
//		logger.error("this is error");
		log.trace("this is trace");
		log.debug("this is debug");
		log.info("this is info");
		log.warn("this is warn");
		log.error("this is error");
	}

}
