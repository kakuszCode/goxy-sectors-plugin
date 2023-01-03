package pl.robalmeister.goxysectorsplugin.task;

import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;
import pl.goxy.minecraft.api.GoxyApi;
import pl.goxy.minecraft.pubsub.PubSub;
import pl.robalmeister.goxysectorsplugin.config.Configuration;
import pl.robalmeister.goxysectorsplugin.data.redis.SectorDataRedis;
import pl.robalmeister.goxysectorsplugin.pubsub.PubSubAbstract;

public class UpdateDataSectorTask extends PubSubAbstract implements Runnable {
    private SectorDataRedis sectorDataRedis;


    public UpdateDataSectorTask(PubSub pubSub, Configuration configuration) {
        super(pubSub);
        this.sectorDataRedis = new SectorDataRedis(configuration.getMainSector().getName(), configuration.getMainSector().getUuidServer(),configuration.getMainSector().getSectorDirectionEnum(), MinecraftServer.getServer().recentTps[0], Bukkit.getOnlinePlayers().size());
    }

    @Override
    public void run() {
        load();
    }

    @Override
    public void load() {
        sectorDataRedis.setPlayers(Bukkit.getOnlinePlayers().size());
        sectorDataRedis.setTps(MinecraftServer.getServer().recentTps[0]);
        pubSub.sendPluginContainer("getSectorData", sectorDataRedis, GoxyApi.getNetworkManager().getContainer());
    }
}
