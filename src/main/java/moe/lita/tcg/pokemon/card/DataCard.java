package moe.lita.tcg.pokemon.card;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@Getter(AccessLevel.PUBLIC)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
public class DataCard extends BaseCard {
    String set;

    public static DataCardBuilder<?, ?> toBuilder(BaseCard p) {
        return new DataCardBuilderImpl().$fillValuesFromParent(p);
    }
}
