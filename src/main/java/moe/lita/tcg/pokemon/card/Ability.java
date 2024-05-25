package moe.lita.tcg.pokemon.card;

import java.util.List;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Ability {
    String name;
    String text;
    String type;
}
