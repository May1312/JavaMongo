package com.test.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.bean.User;
import com.test.service.MongoService;

@RequestMapping("/mongo")
@Controller
public class MongoController {
	
	@Autowired
	private MongoService mongoService;

	/*@RequestMapping(value="/receive",method=RequestMethod.POST)
	public void receiveDate(HttpServletRequest request){
		System.out.println(request.getParameter("name")+request.getParameter("age")+request.getParameter("sex"));
		//mongoService.add(user);
		System.out.println("------");
	}*/
	@RequestMapping(value="/receive",method=RequestMethod.POST)
	public ResponseEntity<Map<Object,Object>> receiveDate(@RequestBody User user){
		//mongoService.add(user);
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("message", "this method request is successful");
		return ResponseEntity.ok(map);
	}
	@RequestMapping(value="/show",method=RequestMethod.GET)
	public String show(){
		return "user";
	}
}
