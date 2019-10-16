package com.santander.examen.perfiles.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger configuracion
 * @author Edgar Jonathan Miranda Nava - emiranda882@gmail.com
 * @Since 1.0
 */

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket usersApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(usersApiInfo())
                .select()
                .paths(userPaths())
                .apis(RequestHandlerSelectors.any())
                .build()
                .useDefaultResponseMessages(false);
    }


    private ApiInfo usersApiInfo() {
        return new ApiInfoBuilder()
                .title("Servicio de perfiles")
                .version("1.0")
                .license("Apache License Version 2.0 And Santander")
                .build();
    }


    private Predicate<String> userPaths() {
        return regex("/app.*");
    }
}
