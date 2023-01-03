package pl.robalmeister.goxysectorsplugin.helper;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

@UtilityClass
public class ChatHelper {
    private final LegacyComponentSerializer legacyComponentSerializer = LegacyComponentSerializer.builder()
            .hexColors()
            .character('&')
            .hexCharacter('#')
            .useUnusualXRepeatedCharacterHexFormat()
            .extractUrls()
            .build();

    public Component parse(String text){
        return legacyComponentSerializer.deserialize(text);
    }
    public String deserialize(Component component){
        return legacyComponentSerializer.serialize(component);
    }
}
