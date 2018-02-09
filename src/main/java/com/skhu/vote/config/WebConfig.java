package com.skhu.vote.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	
	private static final String[] EXCLUDE_PATHS = {
			"/admin/signin"
	};
	
	@Autowired
	JwtInterceptor jwtInterceptor;
	
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns(EXCLUDE_PATHS);
	}
	
}
