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

    public void evictApartmentIdsCache(List<Integer> apartmentIds) {
        log.info("method 'evictApartmentIdsCache'");
        if(apartmentIds == null || apartmentIds.isEmpty()) {
            return;
        }
        Cache apartmentsCache = cacheManager.getCache("apartments");
        if(apartmentsCache != null) {
            log.info("evicting apartmentsCache in method 'evictApartmentIdsCache'");
            apartmentIds.forEach(apartmentsCache::evict);
        }
    }
}
