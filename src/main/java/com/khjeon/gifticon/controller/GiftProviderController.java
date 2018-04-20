package com.khjeon.gifticon.controller;

import com.khjeon.gifticon.domain.GiftProvider;
import com.khjeon.gifticon.repository.GiftProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * Created by PenPen on 2018. 4. 20..
 */
@RestController
@RequestMapping("/giftProviders")
public class GiftProviderController {

	@Autowired
	private GiftProviderRepository repository;

	@PostMapping(value = "")
	public GiftProvider add(String companyName){
		GiftProvider gp = new GiftProvider();
		gp.setCompanyName(companyName);

		GiftProvider saved = repository.save(gp);

		return saved;
	}

	@GetMapping(value = "")
	public List<GiftProvider> getAll() {
		return repository.findAll();
	}

	@GetMapping(value = "/{id}")
	public GiftProvider get(@PathVariable int id, HttpServletResponse response) {
		GiftProvider gp = new GiftProvider();
		gp.setId(id);

		Optional<GiftProvider> one = repository.findOne(Example.of(gp));
		if (one.isPresent()) {
			return one.get();
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}
