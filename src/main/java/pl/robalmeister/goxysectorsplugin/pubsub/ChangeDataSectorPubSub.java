package pl.robalmeister.goxysectorsplugin.pubsub;

import pl.goxy.minecraft.pubsub.PubSub;
import pl.robalmeister.goxysectorsplugin.data.redis.SectorDataRedis;
import pl.robalmeister.goxysectorsplugin.data.redis.service.SectorDataRedisService;

public class ChangeDataSectorPubSub extends PubSubAbstract {
    protected ChangeDataSectorPubSub(PubSub pubSub) {
        super(pubSub);
    }

    @Override
    public void load() {
        pubSub.registerHandler("getSectorData", SectorDataRedis.class, (pubSubContext, sectorData) -> {
            SectorDataRedisService.getInstance().getSectors().remove(sectorData.getName());
            SectorDataRedisService.getInstance().getSectors().put(sectorData.getName(), sectorData);
        }).async(true);
    }
}
