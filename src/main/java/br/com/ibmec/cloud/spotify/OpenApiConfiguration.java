package br.com.ibmec.cloud.spotify;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ibmec Cloud 2024.1")
                        .description("Spotify II Cloud Project")
                        .version("1.0")
                        .contact(new Contact()
                                .email("lobivitor1@gmail.com")
                                .name("Vitor Lobianco")
                        )
                );
    }
}
