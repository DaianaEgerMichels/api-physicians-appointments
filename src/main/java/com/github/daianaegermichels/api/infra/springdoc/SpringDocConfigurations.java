package com.github.daianaegermichels.api.infra.springdoc;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("Appointments API")
                        .description("Rest API of the medical application, containing CRUD functionalities for doctors and patients, in addition to scheduling and canceling appointments")
                        .contact(new Contact()
                                .name("Time Backend")
                                .email("backend@appointments.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://appointments.com/api/license")));
    }
}
