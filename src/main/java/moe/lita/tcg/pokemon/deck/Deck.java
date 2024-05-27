package moe.lita.tcg.pokemon.deck;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import moe.lita.tcg.pokemon.card.Type;

@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class Deck {
    String id;
    String name;
    List<Type> types;
    List<DeckCard> cards;
}
