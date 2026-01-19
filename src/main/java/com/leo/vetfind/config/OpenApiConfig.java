package com.leo.vetfind.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                .title("VetFind API")
                .version("v1")
                .description("REST API for connecting pet owners with veterinarians")
                .contact(new Contact().name("Leonardo Ferreira").email("leonardo.lmf27@gmail.com")));
    }
}
