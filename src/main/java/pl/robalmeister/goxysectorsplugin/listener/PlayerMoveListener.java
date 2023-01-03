package pl.robalmeister.goxysectorsplugin.listener;

import eu.okaeri.injector.annotation.Inject;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pl.goxy.minecraft.api.GoxyApi;
import pl.goxy.minecraft.api.network.GoxyConnector;
import pl.goxy.minecraft.api.network.GoxyServer;
import pl.goxy.minecraft.pubsub.PubSub;
import pl.robalmeister.goxysectorsplugin.config.Configuration;
import pl.robalmeister.goxysectorsplugin.data.SectorData;
import pl.robalmeister.goxysectorsplugin.data.SectorDirectionEnum;
import pl.robalmeister.goxysectorsplugin.data.user.SectorsUser;
import pl.robalmeister.goxysectorsplugin.helper.ChatHelper;
import pl.robalmeister.goxysectorsplugin.pubsub.PubSubAbstract;
import pl.robalmeister.goxysectorsplugin.serializer.ItemSerializer;

import java.util.List;

public class PlayerMoveListener implements Listener {
    @Inject
    private Configuration configuration;
    @Inject
    private BukkitAudiences adventure;
    @Inject
    private PubSub pubSub;
    //test



    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (e.getFrom() == e.getTo()) return;
        Location locationTo = e.getTo();
        assert locationTo != null;
        if (!(locationTo.getX() >= -locationTo.getWorld().getWorldBorder().getSize() - 2 && locationTo.getX() <= locationTo.getWorld().getWorldBorder().getSize() + 2 && locationTo.getZ() >= -locationTo.getWorld().getWorldBorder().getSize() - 2 && locationTo.getZ() <= locationTo.getWorld().getWorldBorder().getSize() + 2)) {
            SectorDirectionEnum direction = getDirection(locationTo);
           configuration.getSectorData().stream().filter(sectorData -> sectorData.getSectorDirectionEnum() == direction).forEach(sectorData -> {
               GoxyServer server = GoxyApi.getNetworkManager().getServer(sectorData.getUuidServer());
               pubSub.sendServer("getSectorUser", new SectorsUser(p.getUniqueId(),
                       p.getName(),
                       ItemSerializer.serialize(p.getInventory().getContents()),
                       ItemSerializer.serialize(p.getInventory().getArmorContents()),
                       p.getGameMode(),
                       p.getFoodLevel(),
                       p.getHealth(),
                       p.getLevel(),
                       p.getExp(),
                       p.isFlying()),server).thenAccept(unused -> {
                           p.getInventory().setContents(null);
                           p.getInventory().setArmorContents(null);
                           adventure.player(p).sendMessage(ChatHelper.parse("nawiazuje polaczenie z twoim starym"));
                           server.connect(GoxyApi.getPlayerStorage().getPlayer(p.getUniqueId())).thenAccept(connectResult -> {
                                if (connectResult == GoxyConnector.ConnectResult.FAIL){
                                    adventure.player(p).sendMessage(ChatHelper.parse("kurwa nie dziala co jest"));
                                }
                           });
               });

           });

        }

    }


    public SectorDirectionEnum getDirection(Location loc) {
        Validate.notNull(loc, "Location cannot be null");
        if (loc.getX() >= -loc.getWorld().getWorldBorder().getSize() - 2 ) return SectorDirectionEnum.WEST;
        if (loc.getX() >= loc.getWorld().getWorldBorder().getSize() - 2 ) return SectorDirectionEnum.EAST;
        if (loc.getZ() >= loc.getWorld().getWorldBorder().getSize() - 2 ) return SectorDirectionEnum.SOUTH;
        if (loc.getZ() >= -loc.getWorld().getWorldBorder().getSize() - 2 ) return SectorDirectionEnum.NORTH;
        return SectorDirectionEnum.DEFAULT;
    }


}
