package ru.fil.addressservice.service;

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

    public void evictApartmentsCache(List<Integer> apartmentIds) {
        log.info("method 'evictApartmentsCache'");
        if(apartmentIds == null || apartmentIds.isEmpty()) {
            return;
        }
        Cache cache = cacheManager.getCache("apartments");
        if(cache != null) {
            log.info("evicting cache in method 'evictApartmentsCache'");
            apartmentIds.forEach(cache::evict);
        }
    }
}
