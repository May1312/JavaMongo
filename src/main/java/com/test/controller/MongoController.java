package com.test.controller;

import com.test.bean.User;
import com.test.bean.pageBean;
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
	@RequestMapping(value = "/receive", method = RequestMethod.POST)
	public ResponseEntity<Map<Object, Object>> receiveDate(@RequestBody User user) {
		mongoService.add(user);
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("status", "200");
		Map<Object, Object> map2 = new HashMap<Object, Object>();
		map2.put("result", map);
		return ResponseEntity.ok(map);
	}

	@RequestMapping(value = "/showpage", method = {RequestMethod.GET})
	public String  showpage() {
		return "userlist";
	}
	@RequestMapping(value = "/show", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody pageBean queryUser(Model model,@RequestParam(value = "page",defaultValue = "0") int currentPage,@RequestParam(value = "rows",defaultValue = "20") int pageSize) {
		//返回userId
		List<User> users = mongoService.queryUser(currentPage,pageSize);
		//total 所有条数
		int total  = mongoService.queryUserCount();
		/*model.addAttribute("users", users);
		model.addAttribute("total",total);
		return "userlist";*/
		//返回json
		pageBean pb = new pageBean();
		pb.setList(users);
		pb.setTotal(total);
		pb.setPage(currentPage);
		pb.setRows(pageSize);
		return pb;
	}

	@RequestMapping(value = "/remove/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<Object,Object>> remove(@PathVariable("userId") String userId) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			if (StringUtils.isNotBlank(userId)) {
				mongoService.remove(userId);
				//return ResponseEntity.status(HttpStatus.OK).build();
				 map = new HashMap<Object,Object>();
				map.put("msg","remove successful");
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
	}
	@RequestMapping(value="/checkName",method=RequestMethod.GET)
	public ResponseEntity<Map<Object, Object>> checkName(@RequestParam("name") String name){
		int count = mongoService.checkname(name);
		Map<Object,Object> map = new HashMap<Object, Object>();
			map.put("count",count);
			return ResponseEntity.ok(map);
	}
}