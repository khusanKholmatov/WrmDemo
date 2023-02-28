package com.example.wrmdemo.configuration;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;

@Configuration
public class ConnectionFactoryConfiguration {

    @Bean
    ConnectionFactory connectionFactory() {

        PostgresqlConnectionConfiguration configuration =
                PostgresqlConnectionConfiguration.builder()
                        .database("warehouse")
                        .password("husan")
                        .username("postgres")
                        .host("localhost")
                        .build();

        return new PostgresqlConnectionFactory(configuration);
    }

    @Bean
    DatabaseClient databaseClient() {
        return DatabaseClient.create(connectionFactory());
    }

}
