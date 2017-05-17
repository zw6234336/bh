package com.zhang.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/* 
 * Restful API 访问路径: 
 * http://IP:port/{context-path}/swagger-ui.html 
 * eg:http://localhost:8080/jd-config-web/swagger-ui.html 
 */  
@EnableWebMvc  
@EnableSwagger2  
@ComponentScan(basePackages = {"com.zhang.action"})  
@Configuration  
public class RestApiConfig extends WebMvcConfigurationSupport{  
  
    @Bean  
    public Docket createRestApi() {  
        return new Docket(DocumentationType.SWAGGER_2)  
                .apiInfo(apiInfo())  
                .select()  
                .apis(RequestHandlerSelectors.basePackage("com.zhang.action"))  
                .paths(PathSelectors.any())  
                .build();  
    }  
  
    private ApiInfo apiInfo() {  
        return new ApiInfoBuilder()  
                .title("统一销售平台API")  
                .version("1.0")  
                .build();  
    }  
} 

