package pl.robalmeister.goxysectorsplugin.listener;

import eu.okaeri.injector.annotation.Inject;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pl.robalmeister.goxysectorsplugin.config.Configuration;
import pl.robalmeister.goxysectorsplugin.data.SectorData;
import pl.robalmeister.goxysectorsplugin.data.SectorDirectionEnum;
import pl.robalmeister.goxysectorsplugin.helper.ChatHelper;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerMoveListener implements Listener {
    @Inject
    private Configuration configuration;
    @Inject
    private BukkitAudiences adventure;
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (e.getFrom() == e.getTo()) return;
        Location locationTo = e.getTo();
        assert locationTo != null;
        if (!(locationTo.getX() >= -locationTo.getWorld().getWorldBorder().getSize() - 2 && locationTo.getX() <= locationTo.getWorld().getWorldBorder().getSize() + 2 && locationTo.getZ() >= -locationTo.getWorld().getWorldBorder().getSize() - 2 && locationTo.getZ() <= locationTo.getWorld().getWorldBorder().getSize() + 2)){
            SectorDirectionEnum direction = getDirection(locationTo);
            List<SectorData> sectors = configuration.getSectorData().stream().filter(sectorData -> sectorData.getSectorDirectionEnum() == direction).collect(Collectors.toList());
            if (sectors.isEmpty()){
                adventure.player(e.getPlayer()).sendMessage(ChatHelper.parse("pierdol sie kurwy"));
                return;
            }
        }

    }


    public SectorDirectionEnum getDirection(Location loc){


        return SectorDirectionEnum.DEFAULT;
    }
}
