package br.com.estacaometeorologica.adapter.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "API para gerenciamento de informações meteorológicas",
                version = "1.0.0",
                description = "Componente responsável por gerenciar informações meteorológicas",
                contact = @Contact(
                        name = "",
                        email = ""
                )
        ),
        servers = @Server(
                url = "/"
        )
)
@Configuration
class OpenApiConfiguration {
}



