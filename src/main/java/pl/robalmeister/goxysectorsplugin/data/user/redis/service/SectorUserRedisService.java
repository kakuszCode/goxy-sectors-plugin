package pl.robalmeister.goxysectorsplugin.data.user.redis.service;


import pl.robalmeister.goxysectorsplugin.data.user.SectorsUser;


import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SectorUserRedisService {
    private static SectorUserRedisService singleton;
    private final Map<UUID, SectorsUser> sectorsUsers;

    private SectorUserRedisService(){
        sectorsUsers = new ConcurrentHashMap<>();
    }

    public Map<UUID, SectorsUser> getSectorsUsers() {
        return sectorsUsers;
    }

    public static synchronized SectorUserRedisService getInstance() {
        if (singleton == null)
            singleton=new SectorUserRedisService();
        return singleton;
    }
}
