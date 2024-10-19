package br.com.estacaometeorologica.adapter.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Habilita CORS para todos os endpoints
                .allowedOrigins("*")  // Permite requisições de qualquer origem
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos permitidos
                .allowedHeaders("*")  // Permite todos os cabeçalhos
                .allowCredentials(false)  // Defina como `true` se quiser permitir cookies
                .maxAge(3600);  // Tempo máximo que a resposta de pré-verificação pode ser armazenada em cache
    }
}