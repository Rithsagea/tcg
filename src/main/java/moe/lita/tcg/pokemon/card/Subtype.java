package moe.lita.tcg.pokemon.card;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Subtype {
    BASIC("Basic"), STAGE_1("Stage 1"), STAGE_2("Stage 2"), ITEM("Item"), SUPPORTER("Supporter"), STADIUM("Stadium"),
    EX("EX"), TEAM_PLASMA("Team Plasma"), RESTORED("Restored"), POKEMON_TOOL("Pokémon Tool");

    @Getter
    @JsonValue
    private final String name;
}
