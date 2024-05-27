package moe.lita.tcg.pokemon.card;

import java.util.Collection;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonValue;

import discord4j.common.util.Snowflake;
import discord4j.core.object.reaction.ReactionEmoji;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Type {
    COLORLESS("Colorless", "<:colorless_energy:1244029682672992326>"),
    GRASS("Grass", "<:grass_energy:1244029740369580074>"),
    FIRE("Fire", "<:fire_energy:1244029693661806693>"),
    WATER("Water", "<:water_energy:1244029716696928327>"),
    LIGHTNING("Lightning", "<:lightning_energy:1244029689991794731>"),
    PSYCHIC("Psychic", "<:psychic_energy:1244029689358585886>"),
    FIGHTING("Fighting", "<:fighting_energy:1244029688565862410>"),
    DARKNESS("Darkness", "<:darkness_energy:1244029687617818644>"),
    METAL("Metal", "<:metal_energy:1244029686019915899>"),
    DRAGON("Dragon", "<:dragon_energy:1244029683138297958>");

    @Getter
    @JsonValue
    private final String name;

    @Getter
    private final String emote;

    public ReactionEmoji getEmoji() {
        String[] data = emote.split("[<>:]+");
        return ReactionEmoji.custom(Snowflake.of(data[2]), data[1], false);
    }

    // TODO leetcode problem, allow certain strings to be excluded
    public static String format(String s) {
        for (Type t : values()) {
            s = s.replaceAll(t.name, t.emote);
        }

        return s;
    }

    public static String toString(Collection<Type> types) {
        return types == null ? "" : types.stream().map(Type::getEmote).collect(Collectors.joining(""));
    }
}
