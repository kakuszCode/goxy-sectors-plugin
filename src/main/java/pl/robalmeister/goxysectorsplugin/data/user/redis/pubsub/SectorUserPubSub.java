package pl.robalmeister.goxysectorsplugin.data.user.redis.pubsub;

import pl.goxy.minecraft.pubsub.PubSub;
import pl.robalmeister.goxysectorsplugin.data.user.SectorsUser;
import pl.robalmeister.goxysectorsplugin.data.user.redis.service.SectorUserRedisService;
import pl.robalmeister.goxysectorsplugin.pubsub.PubSubAbstract;

public class SectorUserPubSub extends PubSubAbstract {
    protected SectorUserPubSub(PubSub pubSub) {
        super(pubSub);
    }

    @Override
    public void load() {
        pubSub.registerHandler("getSectorUser", SectorsUser.class, (pubSubContext, sectorData) -> SectorUserRedisService.getInstance().getSectorsUsers().put(sectorData.getUuid(), sectorData))
                .async(true);
    }
}
