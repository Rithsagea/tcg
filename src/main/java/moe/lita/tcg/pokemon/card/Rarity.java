package moe.lita.tcg.pokemon.card;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Rarity {
    COMMON("Common"), UNCOMMON("Uncommon"), RARE_HOLO("Rare Holo"), RARE_HOLO_EX("Rare Holo EX"), RARE("Rare"),
    RARE_ULTRA("Rare Ultra"), RARE_SECRET("Rare Secret"), PROMO("Promo");

    @Getter
    @JsonValue
    private final String name;
}
