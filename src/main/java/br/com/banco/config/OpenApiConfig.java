package br.com.banco.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI configSwagger() {
        return new OpenAPI()
                .info(new Info()
                        .title("Supera - Inovação - Tecnologia")
                        .description(" - Processo Seletivo Java")
                        .version("v0.0.1")
                        .license(new License()
                                .name("desafio")
                                .url("bruno_st17@hotmail.com"))
                        .contact(new Contact()
                                .name("Bruno Santos")
                                .url("https://github.com/Brun0Santos")
                                .email("bruno_st17@hotmail.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Github")
                        .url("https://github.com/Brun0Santos"));
    }
}