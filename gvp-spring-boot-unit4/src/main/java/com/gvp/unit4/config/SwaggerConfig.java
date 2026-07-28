package com.gvp.unit4.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI gvpOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("GVP Book Store API")
						.description("A plain CRUD API for books and categories - no database, no security yet.")
						.version("1.0.0"));
	}
}
