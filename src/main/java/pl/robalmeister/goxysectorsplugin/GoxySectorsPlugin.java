package pl.robalmeister.goxysectorsplugin;

import eu.okaeri.injector.Injector;
import eu.okaeri.injector.OkaeriInjector;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pl.goxy.minecraft.pubsub.PubSub;
import pl.goxy.minecraft.pubsub.PubSubService;
import pl.robalmeister.goxysectorsplugin.listener.BlockPlaceListener;
import pl.robalmeister.goxysectorsplugin.listener.PlayerJoinListener;
import pl.robalmeister.goxysectorsplugin.listener.PlayerMoveListener;

import java.util.Objects;

public final class GoxySectorsPlugin extends JavaPlugin {
    private BukkitAudiences adventure;
    private Injector injector;
    private PubSub pubSub;

    //redisa instaluje
    @Override
    public void onEnable() {
        Plugin pubSubPlugin = Objects.requireNonNull(getServer().getPluginManager().getPlugin("goxy-pubsub"), "goxy-pubsub");
        PubSubService pubSubService = (PubSubService) pubSubPlugin;
        this.pubSub = pubSubService.getPubSub(this);

        injector = OkaeriInjector.create();
        adventure = BukkitAudiences.create(this);
        registerVariablesToInjector();
    }

    @Override
    public void onDisable() {
        if(this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
    }
    private void registerVariablesToInjector(){
        injector.registerInjectable(adventure);
        injector.registerInjectable(this);
        injector.registerInjectable(pubSub);
    }
    private void registerListeners(){
        getServer().getPluginManager().registerEvents(injector.createInstance(PlayerMoveListener.class), this);
        getServer().getPluginManager().registerEvents(injector.createInstance(PlayerJoinListener.class),this);
        getServer().getPluginManager().registerEvents(injector.createInstance(BlockPlaceListener.class), this);
    }
}
