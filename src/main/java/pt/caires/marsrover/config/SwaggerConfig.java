package pt.caires.marsrover.config;

import java.time.ZonedDateTime;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * SwaggerConfig class.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("Mars Rover API")
            .select()
            .apis(RequestHandlerSelectors.basePackage("pt.caires.marsrover"))
            .build()
            .directModelSubstitute(ZonedDateTime.class, String.class)
            .useDefaultResponseMessages(false)
            .apiInfo(apiInfo());
    }

    /**
     * API Info as it appears on the swagger-ui page
     */
    private ApiInfo apiInfo()
    {
        return new ApiInfo(
            "Mars Rover API",
            "Video Rental Store API.<BR/>"
                + "<P>System for managing the Mars Rover vehicle</P>",
            "0.1.0",
            "API terms of service URL",
            new Contact("Alejandro Caires", "", ""),
            "License of API",
            "API license URL",
            Collections.emptyList()
        );
    }

}
