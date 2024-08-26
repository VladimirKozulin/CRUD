package com.example.rest.rest.configuration;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
//http://localhost:8080/swagger-ui/index.html
@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI openApiDescription() {
        Server localhostServer = new Server();
        localhostServer.setUrl("http://localhost:8080");
        localhostServer.setDescription("Localhost env");

        Server productionServer = new Server();
        productionServer.setUrl("http://some.prod.upl");
        productionServer.setDescription("Production env");

        Contact contact = new Contact();
        contact.setName("Vladimir Kozulin");
        contact.setEmail("vladimir.kozulin.18@yandex.ru");
        contact.setUrl("https://github.com/VladimirKozulin");

        License license = new License().name("GNU AGPLv3")
                .url("https://choosealicense.com/licesense/agpl-3.0/");

        Info info = new Info()
                .title("Client orders API")
                .version("1.0.0")
                .contact(contact)
                .description("API for client orders")
                .termsOfService("http://some.terms.url")
                .license(license);
        return new OpenAPI().info(info).servers(List.of(localhostServer, productionServer));
    }
}
