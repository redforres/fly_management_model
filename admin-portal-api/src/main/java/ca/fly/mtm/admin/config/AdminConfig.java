package ca.fly.mtm.admin.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@ConfigurationProperties(prefix = "admin", ignoreUnknownFields = false)
@NoArgsConstructor
@Getter
@Setter
public class AdminConfig {
    private String adminFrontendUrl;
    private String adminExtLoginUrl;
    private String secretKey;

}
