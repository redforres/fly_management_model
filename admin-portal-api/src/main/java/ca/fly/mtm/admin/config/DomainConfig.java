package ca.fly.mtm.admin.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("ca.fly.mtm.admin.entity")
//@EnableJpaRepositories("ca.fly.mtm.admin.repository")
@EnableTransactionManagement
public class DomainConfig {
}
