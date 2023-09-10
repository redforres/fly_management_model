package ca.fly.mtm.admin;

import ca.fly.mtm.admin.config.AdminConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties({ AdminConfig.class })
public class AdminPortalApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        log.info("Starting the FLY MTM ADMIN Portal Application");
        SpringApplication.run(AdminPortalApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        log.info("--------Configuring the Servlet -----");
        return application.sources(AdminPortalApplication.class);
    }

    // call 3rd-party api from other endpoints
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
