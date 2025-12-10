package com.tanishk.tms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	
	 @Bean
	 public OpenAPI customOpenAPI() {
	
	return new OpenAPI()
            .info(new Info()
                    .title("Transport Management System (TMS)")
                    .version("1.0")
                    .description("Backend developed by Tanishk Sarraf")
                    .license(new License().name("Github Link").url("https://github.com/Tansarraf"))
                    .contact(new Contact()
                            .name("Tanishk Sarraf")
                            .email("sarraftanishk2@gmail.com")
                            .url("https://tanishk-portfolio-chi.vercel.app/")))
            .externalDocs(new ExternalDocumentation()
                    .description("Project Documentation")
                    .url(""));
	 }
}