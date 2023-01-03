package pl.robalmeister.goxysectorsplugin.data.redis;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import pl.robalmeister.goxysectorsplugin.data.SectorData;

import java.util.UUID;
@Getter
@Setter
public class SectorDataRedis extends SectorData {
    private double tps;

    private int players;

    public SectorDataRedis(@NotNull String name, @NotNull UUID uuidServer, double tps, int players) {
        super(name, uuidServer);
        this.tps = tps;
        this.players = players;
    }
}
