package com.itmuch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/*
 * 自定义Ribbon客户端需要注意点：
 * 	1.必需使用@Configuration修饰
 * 	2.自定义的类不能在主应用程序上下文的@ComponentScan中，否则将由所有@RibbonClients共享。如果您使用@ComponentScan（或@SpringBootApplication），
 * 	则需要采取措施避免包含（例如将其放在一个单独的，不重叠的包中，或者指定要在@ComponentScan）。
 *
 */

@Configuration
public class CustomizingRibbonClientConfiguration {

	@Bean
	// 测试将自定义Ribbon客户端实现类放到与SpringBoot启动类同级目录需要将 IClientConfig config 注释
	public IRule ribbonRule(/* IClientConfig config */) {
		System.out.println("------自定义Ribbon客户端RandomRule()-----");
		return new RandomRule();
	}
}
