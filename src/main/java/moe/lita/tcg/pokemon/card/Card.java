package moe.lita.tcg.pokemon.card;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class Card {
    String id;
    String name;
    Supertype supertype;
    List<Subtype> subtypes;

    Integer hp;
    List<Type> types;
    String evolvesFrom;
    List<String> evolvesTo;

    List<String> rules;
    List<Ability> abilities;
    List<Attack> attacks;
    List<Weakness> weaknesses;
    List<Resistance> resistances;
    List<Type> retreatCost;

    String number;
    String artist;
    Rarity rarity;
    String flavorText;
    List<Integer> nationalPokedexNumbers;
    Legality legalities;

    Images images;
}
