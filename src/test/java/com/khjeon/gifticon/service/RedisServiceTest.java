package com.khjeon.gifticon.service;

import com.khjeon.gifticon.domain.GiftProvider;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisServiceTest {

	@Autowired
	private RedisService redisService;

	@Test
	public void A_put() {
		redisService.put(0, "MY_COMPANY");
	}

	@Test
	public void B_get() {
		GiftProvider giftProvider = redisService.get(0);
		System.out.println(giftProvider);

	}
}
