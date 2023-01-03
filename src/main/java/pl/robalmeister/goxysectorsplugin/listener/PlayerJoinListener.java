package pl.robalmeister.goxysectorsplugin.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.robalmeister.goxysectorsplugin.data.user.SectorsUser;
import pl.robalmeister.goxysectorsplugin.data.user.redis.service.SectorUserRedisService;
import pl.robalmeister.goxysectorsplugin.serializer.ItemSerializer;

public class PlayerJoinListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        SectorsUser user = SectorUserRedisService.getInstance().getSectorsUsers().get(p.getUniqueId()); //kurwa to nie kotlin
        if (user == null) return;
        p.getInventory().setContents(ItemSerializer.deserialize(user.getContents()));
        p.getInventory().setArmorContents(ItemSerializer.deserialize(user.getArmorContents()));
        p.setAllowFlight(user.isFly());
        p.setFlying(user.isFly());
        p.setGameMode(user.getGameMode());
        p.setFoodLevel(user.getFoodLevel());
        p.setHealth(user.getHealthLevel());
        p.setLevel(user.getExpLevel());
        p.setExp(p.getExp());
        //poteznie to zostalo zrobione!
    }
}
