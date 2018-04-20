package com.khjeon.gifticon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by PenPen on 2018. 4. 20..
 */
@RestController
public class TestController {

	@RequestMapping("/test1")
	public String test(){
		return"ASDF";
	}


}
