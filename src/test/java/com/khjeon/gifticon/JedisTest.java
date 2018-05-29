package com.khjeon.gifticon;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.List;

/**
 * Jedis 예제, 이것이 레디스다 참조
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JedisTest {

	@Value("${server.main.host}")
	private String host;

	@Value("${server.main.redis.port}")
	private int port;

	@Test
	public void jedisSimpleConnectionTest() {

		// 간단한 연결, but 매번 커넥션 생성함
		Jedis jedis = new Jedis(host, port);
		String key = "TEST";
		jedis.set(key, "value");
		jedis.expire(key, 100);

		String value = jedis.get("key");
		System.out.println(value);
		System.out.println(jedis.ttl(key));

	}

	@Test
	public void jedisConnectionPoolTest() {

		int poolSize = 20;

		// 커넥션 풀 사용
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		// Object pool 최대개수
		config.setMaxTotal(poolSize);
		// 최대 개수에 도달했을때 전략, BLOCK은 더이상 생성하지 않음
		config.setBlockWhenExhausted(true);

		JedisPool pool = new JedisPool(config, host, port);

		/*
		for (int i = 0; i < poolSize + 1; i++) {
			Jedis j = pool.getResource();
			System.out.println(i);
		}
		*/

		Jedis firstClient = pool.getResource();
		System.out.println(firstClient);

		firstClient.set("key", "changed");

		String listKey = "list1";
		firstClient.lpush(listKey, "AA");
		firstClient.lpush(listKey, "BB");

		List<String> lrange = firstClient.lrange(listKey, 0, -1);// -1은 전부를 뜻함
		lrange.forEach(System.out::println);

		Jedis secondClient = pool.getResource();
		System.out.println(secondClient);
		System.out.println(secondClient.get("key"));

		pool.returnResource(firstClient);
		pool.returnResource(secondClient);


		pool.destroy();
	}

	@Test
	public void jedisWithoutPipeliningTest() {

		Jedis jedis = new Jedis(host, port);

		int totalSize = 1000;

		long start = System.currentTimeMillis();

		for (int i=0;i<totalSize; i++) {
			jedis.set("key" + i, "value" + i);
			if(i % 100 ==0)
				System.out.println(i);
		}

		long end = System.currentTimeMillis();
		System.out.println((end - start) / 1000.0);
	}

	@Test
	public void jedisWithPipeliningTest() {

		Jedis jedis = new Jedis(host, port);
		Pipeline pipeline = jedis.pipelined();

		int totalSize = 1000;

		long start = System.currentTimeMillis();

		for (int i=0;i<totalSize; i++) {
			pipeline.set("pipeline_key" + i, "value" + i);
			if(i % 100 ==0)
				System.out.println(i);
		}

		long end = System.currentTimeMillis();
		System.out.println((end - start) / 1000.0);
	}

}
