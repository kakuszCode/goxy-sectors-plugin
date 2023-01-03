package pl.robalmeister.goxysectorsplugin.data.redis.service;

import pl.robalmeister.goxysectorsplugin.data.redis.SectorDataRedis;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SectorDataRedisService {
    private static SectorDataRedisService singleton;
    private final Map<String, SectorDataRedis> sectors;

    private SectorDataRedisService(){
        sectors = new ConcurrentHashMap<>();
    }

    public Map<String, SectorDataRedis> getSectors() {
        return sectors;
    }

    public static synchronized SectorDataRedisService getInstance() {
        if (singleton == null)
            singleton=new SectorDataRedisService();
        return singleton;
    }

}
