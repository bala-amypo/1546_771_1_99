package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        // Define JWT Bearer Security Scheme
        SecurityScheme jwtScheme = new SecurityScheme()
                .name("Authorization")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER);

        return new OpenAPI()
                .info(new Info()
                        .title("Asset Lifecycle Management API")
                        .description("APIs for Asset, Vendor, Depreciation, Lifecycle Events, Disposal & Auth")
                        .version("1.0.0")
                )
                // Server URL (as given by you)
                .servers(List.of(
                        new Server().url("https://9126.408procr.amypo.ai")
                ))
                // Attach JWT security globally
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("BearerAuth", jwtScheme)
                );
    }
}
