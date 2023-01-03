package pl.robalmeister.goxysectorsplugin.config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.exception.OkaeriException;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import pl.goxy.minecraft.api.GoxyApi;
import pl.robalmeister.goxysectorsplugin.data.SectorData;
import pl.robalmeister.goxysectorsplugin.data.SectorDirectionEnum;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Configuration extends OkaeriConfig {

    private int border = 5000;
    private SectorData mainSector = new SectorData("sector 1", GoxyApi.getNetworkManager().getServer().getId(), SectorDirectionEnum.DEFAULT);

    private List<SectorData> sectorData = Arrays.asList(new SectorData("sector 2", UUID.randomUUID(), SectorDirectionEnum.NORTH), new SectorData("sector 3", UUID.randomUUID(), SectorDirectionEnum.SOUTH));

    @Override
    public OkaeriConfig load() throws OkaeriException {
        super.load();
        Bukkit.getWorlds().forEach(world -> {
            world.getWorldBorder().setCenter(0.0, 0.0);
            world.getWorldBorder().setSize(border);
        });
        return this;
    }
}
