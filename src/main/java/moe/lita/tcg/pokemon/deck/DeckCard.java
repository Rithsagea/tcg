package moe.lita.tcg.pokemon.deck;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import moe.lita.tcg.pokemon.card.Rarity;

@Value
@Builder
@Jacksonized
public class DeckCard {
    String id;
    String name;
    Rarity rarity;
    Integer count;
}
