package edu.octavio.url_shortner.docs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Url shortner API")
                        .description("A url shortner API made using spring framework")
                        .version("v1.0")
                        .license(new License().name("MIT").url("http://springdoc.org"))
                        .contact(new Contact().name("Octavio Piratininga").url("https://github.com/Pira4Ever").email("59701790+Pira4Ever@users.noreply.github.com"))
                        .termsOfService("Terms of use: Open Source"));
    }

}
