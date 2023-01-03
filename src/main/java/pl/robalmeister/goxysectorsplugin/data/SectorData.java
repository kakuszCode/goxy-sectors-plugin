package pl.robalmeister.goxysectorsplugin.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class SectorData {
    @NotNull
    private String name;
    @NotNull
    private UUID uuidServer;

    @NotNull
    private SectorDirectionEnum sectorDirectionEnum;


}
