package ca.fly.mtm.admin;

import ca.fly.mtm.admin.interceptor.RequestLogInterceptor;
import ca.fly.mtm.admin.interceptor.FLYAuthorizeInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;

@EnableWebMvc
@Slf4j
@Configuration
@AutoConfigurationPackage
@ComponentScan("govonca.cbs.vision.mlm.common")
@EnableJpaRepositories("govonca.cbs.vision.mlm.common.repos")
@EntityScan("govonca.cbs.vision.mlm.common.domain")
public class AdminPortalWebConfig implements WebMvcConfigurer{


    @Resource
    private FLYAuthorizeInterceptor flyAuthorizeInterceptor;

    @Autowired
    RequestLogInterceptor logInterceptor;

    @Bean
    public LocaleResolver localeResolverBean() {
        LocaleResolver localeResolver = new LocaleResolver();
        return localeResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("--------Creating the Interceptors -----");
        registry.addInterceptor(flyAuthorizeInterceptor).excludePathPatterns("/swagger-ui.html/**","/swagger-resources/**","/webjars/**");
        registry.addInterceptor(logInterceptor);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("--------Adding CORS Mapping -----");
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("--------Adding static Resource Handler -----");
        // this is to make sure the swagger-ui.html can be loaded in browser
        //registry.addResourceHandler("/**")
        //        .addResourceLocations("classpath:/public/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
