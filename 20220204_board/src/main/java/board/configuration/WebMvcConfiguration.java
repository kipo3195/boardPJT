package board.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import board.interceptor.LoggerInterceptor;




@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor());
		//registry.addInterceptor(new LoggerInterceptor()).addPathPattern 혹은 excludePathPatter 메서드를 통해 경로 지정가능함.
		
		
		
	}
	
}
