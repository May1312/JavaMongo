package com.test.controller;

import com.test.bean.User;
import com.test.service.MongoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
	@RequestMapping(value="/remove/{userId}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> remove(@PathVariable("userId") String userId){
		try {
			if(StringUtils.isNotBlank(userId)){
                mongoService.remove(userId);
				//return ResponseEntity.status(HttpStatus.OK).build();
				return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            }
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
