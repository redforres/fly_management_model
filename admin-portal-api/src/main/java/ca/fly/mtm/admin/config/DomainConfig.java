package ca.fly.mtm.admin.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("govonca.cbs.vision.mlm.public_portal.domain")
@EnableJpaRepositories("govonca.cbs.vision.mlm.public_portal.repos")
@EnableTransactionManagement
public class DomainConfig {
}
