package moe.lita.tcg.pokemon.card;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Supertype {
    POKEMON("Pokémon"), TRAINER("Trainer"), ENERGY("Energy");

    @Getter
    @JsonValue
    private final String name;
}
