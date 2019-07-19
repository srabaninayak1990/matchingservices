package com.walmart.matchingservices.swagger;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
		
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
           .apis(RequestHandlerSelectors.basePackage("com.walmart.matchingservices.controller"))
           .paths(PathSelectors.any())
           .build()
           .apiInfo(metaData());
     }
    
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Matching-Service",
                "This service allow us to identify and assign technicians to various customer issues",
                "1.0",
                "Terms of service",
                new Contact("Walamrt Store Systems Team", "", "Srabaninayak09@gmail.com"),
                "",
                "", new ArrayList<VendorExtension>());
        return apiInfo;
    }
}
