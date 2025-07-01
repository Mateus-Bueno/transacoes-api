package com.leticia.categorias.config; // Altere esse pacote conforme o seu projeto

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl("https://categorias-api-production.up.railway.app");
        server.setDescription("Servidor Railway HTTPS");

        return new OpenAPI()
                .info(new Info()
                        .title("API de Categorias")
                        .version("v1")
                        .description("Documentação da API de Categorias"))
                .servers(List.of(server));
    }
}