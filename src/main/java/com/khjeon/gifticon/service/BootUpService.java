package com.khjeon.gifticon.service;

import com.khjeon.gifticon.domain.GiftProvider;
import com.khjeon.gifticon.repository.GiftProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BootUpService {

	@Autowired
	private GiftProviderRepository repository;

	public void setup() {
		GiftProvider gp1 = new GiftProvider(1, "A");
		GiftProvider gp2 = new GiftProvider(2, "B");
		GiftProvider gp3 = new GiftProvider(3, "C");

		repository.save(gp1);
		repository.save(gp2);
		repository.save(gp3);
	}

}
