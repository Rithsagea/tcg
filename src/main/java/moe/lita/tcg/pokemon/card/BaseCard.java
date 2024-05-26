package moe.lita.tcg.pokemon.card;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Value
@NonFinal
@SuperBuilder(toBuilder = true)
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseCard {
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

    public abstract static class BaseCardBuilder<C extends BaseCard, B extends BaseCard.BaseCardBuilder<C, B>> {
        protected B $fillValuesFromParent(BaseCard instance) {
            $fillValuesFromInstanceIntoBuilder(instance, this);
            return self();
        }
    }

}
