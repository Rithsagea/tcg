package moe.lita.tcg.pokemon.card;

import java.util.List;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Attack {
    String name;
    List<Type> cost;
    int convertedEnergyCost;
    String damage; // TODO deserialize further
    String text;
}
