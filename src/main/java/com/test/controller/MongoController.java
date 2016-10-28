package com.test.controller;

import com.test.bean.User;
import com.test.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		mongoService.add(user);
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("status", "200");
		Map<Object,Object> map2 = new HashMap<Object,Object>();
		map2.put("result",map);
		return ResponseEntity.ok(map);
	}
	@RequestMapping(value="/show",method=RequestMethod.GET)
	public String queryUser(Model model){
		//返回userId
		List<User> users = mongoService.queryUser();
		model.addAttribute("users",users);
		return "userlist";
	}
	//@RequestMapping(value="/show",method=RequestMethod.GET)
	public String show(){
		return "user";
	}
}
