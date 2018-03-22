package com.itmuch.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.itmuch.cloud.entity.User;

@RestController
public class MovieController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	LoadBalancerClient loadBalancerClient;

	@GetMapping("/movie/{id}")
	public User findById(@PathVariable Long id) {
		/***
		 * 未集成Ribbon的时候这里使用IP+端口的试试请求URL地址，集成Ribbon使用服务提供者的服务名，即是application.myl文件中配置的 spring: application: name: provider-user
		 */

		// return restTemplate.getForObject("http://localhost:7900/simple/" + id, User.class);
		return restTemplate.getForObject("http://provider-user/simple/" + id, User.class);
	}

	@GetMapping("/test")
	public String testCustonizingRibbonClientConfiguration() {
		//使用LoadBalancerClient获取相应的信息
		ServiceInstance si = this.loadBalancerClient.choose("provider-user");
		System.out.println("[" + si.getServiceId() + ":" + si.getHost() + ":" + si.getPort() + "]");

		ServiceInstance sione = this.loadBalancerClient.choose("provider-user-01");
		System.out.println("[" + sione.getServiceId() + ":" + sione.getHost() + ":" + sione.getPort() + "]");
		return "success";
	}
}
