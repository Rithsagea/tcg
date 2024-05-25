package moe.lita.tcg.pokemon.card;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Images {
    String small;
    String large;
}
