//package ca.fly.mtm.admin.config;
//
//import ca.fly.mtm.admin.util.StringUtils;
//import lombok.extern.slf4j.Slf4j;
//import oracle.ucp.jdbc.PoolDataSource;
//import oracle.ucp.jdbc.PoolDataSourceFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.jndi.JndiTemplate;
//
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//import java.sql.SQLException;
//
//@Configuration
//@Slf4j
//public class DataSourceConfig {
//
//    @Value("${spring.datasource.url}")
//    private String url;
//    @Value("${spring.datasource.username}")
//    private String username;
//    @Value("${spring.datasource.password}")
//    private String password;
//    @Value("${spring.datasource.oracleucp.minPoolSize}")
//    private String minPoolSize;
//
//    @Value("${spring.datasource.oracleucp.maxPoolSize}")
//    private String maxPoolSize;
//
//
//    @Autowired
//    Environment environment;
//
//    @Bean
//    public DataSource getDataSource() throws NamingException {
//
//       /* String dsJndiName = environment.getProperty("spring.datasource.jndi-name");
//        if (!StringUtils.isStringNUllorEmpty(dsJndiName)) {
//
//            log.info("initialize the data source: jdbc/webvision");
//
//            try {
//                DataSource ds = (DataSource) new JndiTemplate()
//                        .lookup(environment.getProperty("spring.datasource.jndi-name"));
//
//                return ds;
//            }
//            catch(Exception e){
//                log.info("Failed to connect: jdbc/webvision, trying local connection pool");
//            }
//        }*/
//
//        log.info("initialize the local data source:");
//        PoolDataSource pds = null;
//        try {
//            pds = PoolDataSourceFactory.getPoolDataSource();
//
//            pds.setConnectionFactoryClassName("oracle.jdbc.OracleDriver");
////            pds.setConnectionFactoryClassName("oracle.jdbc.org.postgresql.Driver");
//            //pds.setConnectionFactoryClassName("oracle.jdbc.drive.OracleDriver");
//            pds.setURL(url);
//            pds.setUser(username);
//            pds.setPassword(password);
//            pds.setMinPoolSize(Integer.valueOf(minPoolSize));
//            pds.setInitialPoolSize(10);
//            pds.setMaxPoolSize(Integer.valueOf(maxPoolSize));
//
//        } catch (SQLException ea) {
//            log.error("Error connecting to the database: " + ea.getMessage());
//        }
//
//        return pds;
//
//    }
//}
