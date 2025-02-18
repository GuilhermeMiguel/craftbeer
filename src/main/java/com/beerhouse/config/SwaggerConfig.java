package com.beerhouse.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

	@Bean
	public Docket greetingApi() {
		 var parameter = new ParameterBuilder().name("Authorization")                 
	                         .modelRef(new ModelRef("string"))
	                         .parameterType("header")              
	                         .defaultValue("Basic c2Vuc2VkaWE6MjAyMQ==")
	                         .required(true)                
	                         .build();
	     
	        
	        
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.beerhouse"))
				.paths(PathSelectors.ant("/**"))
				.build().apiInfo(metaData())
				.globalOperationParameters(Arrays.asList(parameter));
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("Testes Integracao REST API")
				.description("API craftBeer").build();
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
