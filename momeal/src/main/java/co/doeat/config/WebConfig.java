package co.doeat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${momeal.saveImg}")
	private String saveImg;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/mm_images/**").addResourceLocations("file:///" + saveImg).setCachePeriod(20);
	}

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("/base/home");
		registry.addViewController("/home").setViewName("/base/home");
		registry.addViewController("/main").setViewName("/base/home");
		
		registry.addRedirectViewController("/logout", "/login"); // 로그아웃시 로그인페이지로 리다이렉트
	}

}
