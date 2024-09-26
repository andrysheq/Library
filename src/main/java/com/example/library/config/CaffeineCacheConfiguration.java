package com.example.library.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;

/**
 * Конфигурация кэширования
 */
@Configuration
@EnableCaching
public class CaffeineCacheConfiguration {

    @Bean
    @Primary
    public CacheManager cacheManager() {
        Caffeine<Object, Object> caffeineCacheBuilder =
                Caffeine.newBuilder()
                        .expireAfterAccess(
                                20, TimeUnit.MINUTES)
                        .expireAfterWrite(20, TimeUnit.MINUTES);

        CaffeineCacheManager cacheManager =
                new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeineCacheBuilder);
        return cacheManager;
    }

    @Bean("shortCacheManager")
    public CacheManager shortCacheManager() {
        Caffeine<Object, Object> caffeineCacheBuilder =
                Caffeine.newBuilder()
                        .expireAfterAccess(
                                2, TimeUnit.MINUTES)
                        .expireAfterWrite(2, TimeUnit.MINUTES);

        CaffeineCacheManager cacheManager =
                new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeineCacheBuilder);
        return cacheManager;
    }
}

