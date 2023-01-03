package pl.robalmeister.goxysectorsplugin.listener;

import eu.okaeri.injector.annotation.Inject;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import pl.robalmeister.goxysectorsplugin.helper.ChatHelper;

public class BlockPlaceListener implements Listener {
    @Inject
    private BukkitAudiences adventure;
    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if (calculate(e.getBlockPlaced().getLocation())){
            adventure.player(p).sendMessage(ChatHelper.parse("&4Błąd: &cNie mozesz postawiac bloku przy granicy z sektorem!"));//pozniej bedzie w configu
            e.setCancelled(true);
        }
    }

    private boolean calculate(Location blockLocation){
        Validate.notNull(blockLocation.getWorld(), "world isn't null!");
        return !(blockLocation.getX() >= (-blockLocation.getWorld().getWorldBorder().getSize()) + 20 && blockLocation.getX() <= blockLocation.getWorld().getWorldBorder().getSize() - 20 && blockLocation.getZ() >= (-blockLocation.getWorld().getWorldBorder().getSize()) + 20 && blockLocation.getZ() <= blockLocation.getWorld().getWorldBorder().getSize() - 20);
    }
}
