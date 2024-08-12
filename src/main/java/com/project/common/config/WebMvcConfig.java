package com.project.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.common.FileManagerService;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	// 이미지 path와 서버에 업로드 된 실제 이미지와 매핑 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/images/**") 
				.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH); // 실제 이미지 파일 위치

	}
}
