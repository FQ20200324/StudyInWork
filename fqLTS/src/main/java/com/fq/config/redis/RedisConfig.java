package com.fq.config.redis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;

/**
 * 需要从不同的 redis db 中读写数据，因此不能用 spring.redis 的自动配置
 */
@Configuration
public class RedisConfig {

    @Bean
    @Qualifier("default")
    @ConfigurationProperties(prefix = "redis.default")
    @Primary
    public RedisConfiguration defaultRedisConfig() {
        return new RedisStandaloneConfiguration();
    }

    @Bean
    @Primary
    @Qualifier("default")
    public RedisConnectionFactory defaultRedisConnFactory(@Qualifier("default") RedisConfiguration config,
                                                          @Value("${redis.default.timeout:5000}") Integer timeout) {
        return new LettuceConnectionFactory(
                config,
                LettuceClientConfiguration.builder()
                        .commandTimeout(Duration.ofMillis(timeout))
                        .build()
        );
    }

    @Bean
    @Primary
    @Qualifier("default")
    public StringRedisTemplate defaultRedisTemplate(@Qualifier("default") RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(factory);
        return template;
    }

}
