package com.hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@ComponentScan(basePackages = "com.hospital")
@EntityScan(basePackages = { "com.hospital.model" })
@EnableJpaRepositories(basePackages = { "com.hospital" })
public class HospitalApplication {


	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}
	
	
	 	@Bean
	    public WebMvcConfigurer webMvcConfigurer() {
	        return new WebMvcConfigurer() {
	            @Override
	            public void addResourceHandlers(ResourceHandlerRegistry registry) {
	                registry.addResourceHandler("/image/**")
	                        .addResourceLocations("file:src/main/resources/static/image/");
	            }
	        
	        };
	    }
	 	
	 	
	    @Configuration
        public class webConfig implements WebMvcConfigurer {

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/images/**")
                        .addResourceLocations("file:uploads/");
            }
        }

}
