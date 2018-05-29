package com.khjeon.gifticon.service;

import com.khjeon.gifticon.domain.GiftProvider;
import com.khjeon.gifticon.repository.GiftProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RedisService {

	private final static String REDIS_KEY = "GP:";

	@Autowired
	private GiftProviderRepository gpRepository;

	/**
	 * RedisTemplate 사용 예제
	 * 참고 : http://kingbbode.tistory.com/25 [개발노트 - kingbbode]
	 */
	@Autowired
	private RedisTemplate redisTemplate;

	@Resource(name = "redisTemplate")
	private ValueOperations<String, Object> valueOperations;

	/*
	@Resource(name = "redisTemplate")
	private ListOperations<String, String> listOperations;

	@Resource(name = "redisTemplate")
	private HashOperations<String, String, String> hashOperations;

	@Resource(name = "redisTemplate")
	private SetOperations<String, String> setOperations;

	@Resource(name="redisTemplate")
	private ZSetOperations<String, String> zSetOperations;
	*/


	public GiftProvider get(int id) {
		return (GiftProvider) valueOperations.get(REDIS_KEY + id);
	}

	public GiftProvider put(int id, String name) {
		GiftProvider gp = new GiftProvider(id, name);
		redisTemplate.opsForValue().set(REDIS_KEY + id, gp);
		return gp;
	}

	@Cacheable("GP_ALL")
	public List<GiftProvider> getAll() {
		return gpRepository.findAll();
	}
}
