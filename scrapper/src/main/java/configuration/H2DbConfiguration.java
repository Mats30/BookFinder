package configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("dev")
public class H2DbConfiguration {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "datasource")
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }
}
