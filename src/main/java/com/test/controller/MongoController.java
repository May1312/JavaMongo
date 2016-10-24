package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.bean.User;
import com.test.service.MongoService;

@RequestMapping("/mongo")
@Controller
public class MongoController {
	
	@Autowired
	private MongoService mongoService;

	@RequestMapping(value="/receive",method=RequestMethod.GET)
	public void receiveDate(){
		User user = new User();
		mongoService.add(user);
		System.out.println("------");
	}
}
