package koitt.ratta.doeat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * @author 진민영
 * zepinos.tistory.com/36
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	// 실제 파일의 위치
	@Value("${resources.location}")
	private String resourcesLocation;
	
	// 사용할 path
	@Value("${resources.uri_path:}")
	private String resourcesUriPath;
	
	@Override
	public void addResourceHandlers (ResourceHandlerRegistry registry) {
		registry.addResourceHandler(resourcesUriPath + "/**")
				.addResourceLocations("file:.." + resourcesLocation);
	}

}
