package com.bl.ep.config;

import com.bl.ep.interceptor.ParamInterceptor;
import com.bl.ep.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

	/**
	 * 在请求处理之前进行调用（Controller方法调用之前）
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/**
		 * 拦截器按照顺序执行
		 */
//		registry.addInterceptor(new ParamInterceptor()).addPathPatterns("/**");
//		registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**").excludePathPatterns("login","index");

		super.addInterceptors(registry);
	}


}