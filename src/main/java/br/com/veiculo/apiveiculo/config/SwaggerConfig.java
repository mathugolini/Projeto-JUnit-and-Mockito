package br.com.veiculo.apiveiculo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Aplicação DTO de Veiculo",
        version="1.0",
        description="Projeto DTO de Veiculo"))
public class SwaggerConfig {
}
