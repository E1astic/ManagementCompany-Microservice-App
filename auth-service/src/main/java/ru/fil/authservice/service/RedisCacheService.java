package ru.fil.authservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisCacheService {

    private final CacheManager cacheManager;

    public void evictEmailsCache(List<Integer> userIds) {
        log.info("method 'evictEmailsCache'");
        if(userIds == null || userIds.isEmpty()) {
            return;
        }
        Cache emailsCache = cacheManager.getCache("emails");
        if(emailsCache != null) {
            log.info("evicting emailsCache in method 'evictEmailsCache'");
            userIds.forEach(emailsCache::evict);
        }
    }
}
