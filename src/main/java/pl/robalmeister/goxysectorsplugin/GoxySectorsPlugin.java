package pl.robalmeister.goxysectorsplugin;

import eu.okaeri.injector.Injector;
import eu.okaeri.injector.OkaeriInjector;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;

public final class GoxySectorsPlugin extends JavaPlugin {
    private BukkitAudiences adventure;
    private Injector injector;


    @Override
    public void onEnable() {
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
    }
}
