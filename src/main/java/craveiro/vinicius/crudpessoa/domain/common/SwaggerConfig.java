package craveiro.vinicius.crudpessoa.domain.common;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(servers = {
        @Server(url = "https://spring-java-crudpessoa-adfbea863a8b.herokuapp.com/", description = "Api server"),
        @Server(url = "http://localhost:8080/", description = "Localhost Server"),
})
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(info());
    }

    private Info info() {
        return new Info().title("Crud Pessoa API")
                .description("Documentação do Crud Pessoa")
                .version("V0");
    }
}
