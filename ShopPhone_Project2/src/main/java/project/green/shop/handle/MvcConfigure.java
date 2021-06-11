package project.green.shop.handle;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MvcConfigure implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/profile").setViewName("/profile");
		WebMvcConfigurer.super.addViewControllers(registry);
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		exposeDirectory("custumer-photo", registry);
		exposeDirectory("image-products", registry);
		 registry.addResourceHandler("/resources/**").addResourceLocations("/static/images/**")
         .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
	}
	
	private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
		Path uploadDir = Paths.get(dirName);
		String uploadPath = uploadDir.toFile().getAbsolutePath();
		
		System.out.println("exposeDirectory: "  + uploadPath);
		
		if (dirName.startsWith("../")) {
			dirName.replace("../", "");
		}
		
		registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:" + uploadPath + "/");

	}
}
