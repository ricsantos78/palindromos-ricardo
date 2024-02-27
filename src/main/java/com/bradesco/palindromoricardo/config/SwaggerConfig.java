package com.bradesco.palindromoricardo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
    public OpenAPI customOpenAPI() {

      Info info = new Info()
              .title("Caça Palíndromos")
              .description("API para encontrar e salvar palíndromos em uma matriz")
              .version("1.0.0");
        return new OpenAPI().info(info);
    }
}
