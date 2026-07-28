package com.gvp.unit4.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class StartupInfoLogger implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(StartupInfoLogger.class);

	private final Environment environment;

	public StartupInfoLogger(Environment environment) {
		this.environment = environment;
	}

	@Override
	public void run(String... args) {
		String port = environment.getProperty("server.port");
		log.info("gvp-spring-boot-unit4 ready");
		log.info(" Active profile(s) : {}", String.join(", ", environment.getActiveProfiles()));
		log.info(" Swagger UI        : http://localhost:{}/swagger-ui.html", port);
	}
}
