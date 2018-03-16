package com.itmuch.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.itmuch.cloud.entity.User;

@RestController
public class MovieController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/movie/{id}")
	public User findById(@PathVariable Long id) {
		return restTemplate.getForObject("http://provider-user/simple/" + id, User.class);
	}
}
