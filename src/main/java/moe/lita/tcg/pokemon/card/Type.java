package moe.lita.tcg.pokemon.card;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Type {
    COLORLESS("Colorless"), GRASS("Grass"), FIRE("Fire"), WATER("Water"), LIGHTNING("Lightning"), PSYCHIC("Psychic"),
    FIGHTING("Fighting"), DARKNESS("Darkness"), METAL("Metal"), DRAGON("Dragon");

    @Getter
    @JsonValue
    private final String name;
}
