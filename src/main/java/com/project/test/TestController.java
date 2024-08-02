package com.project.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.post.mapper.PostMapper;


	
@Controller
public class TestController {
	
	@Autowired
	PostMapper postMapper;
	
	@ResponseBody
    @RequestMapping("/test1")
    public String helloWorld() {
        return "Hello world!";
    }
	@ResponseBody
	@GetMapping("/test2")
	public Map<String,Object> test2(){
		Map<String,Object> map = new HashMap<>();
		map.put("a", 1);
		map.put("b", 2);
		map.put("c", 3);
		return map;
	}
	@GetMapping("/test3")
	public String test3(){
		return "test/test";
	}
	@ResponseBody
	@GetMapping("/test4")
	public List<Map<String,Object>> test4(){
		return postMapper.testList();
	}
}