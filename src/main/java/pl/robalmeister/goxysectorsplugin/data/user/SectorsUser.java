package pl.robalmeister.goxysectorsplugin.data.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bukkit.GameMode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SectorsUser {
    @NotNull
    private UUID uuid;

    @Nullable
    private String nickName;

    @Nullable
    private String contents;

    @Nullable
    private String armorContents;

    @NotNull
    private GameMode gameMode;


    private int foodLevel;
    private double healthLevel;
    private int expLevel;
    private float exp;
    private boolean fly;
}
