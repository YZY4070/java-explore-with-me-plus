package ru.practicum.service.stats.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class StatsClientConfig {

    @Bean
    public StatsClient statsClient() {
        return new StatsClient("${stats-server.url}");
    }
}