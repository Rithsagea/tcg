package moe.lita.tcg.discord.embeds;

import java.util.ArrayList;
import java.util.List;

import discord4j.core.spec.EmbedCreateFields.Field;
import discord4j.core.spec.EmbedCreateSpec;
import moe.lita.tcg.pokemon.card.Ability;
import moe.lita.tcg.pokemon.card.Attack;
import moe.lita.tcg.pokemon.card.DataCard;
import moe.lita.tcg.pokemon.card.Resistance;
import moe.lita.tcg.pokemon.card.Subtype;
import moe.lita.tcg.pokemon.card.Supertype;
import moe.lita.tcg.pokemon.card.Type;
import moe.lita.tcg.pokemon.card.Weakness;

public class CardEmbed {
    EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

    private static Field attackField(Attack a) {
        StringBuilder title = new StringBuilder();
        title.append(Type.toString(a.getCost()));
        title.append(String.format(" %s", a.getName()));
        if (!a.getDamage().isBlank())
            title.append(String.format(" (%s)", a.getDamage()));

        String text = a.getText().isBlank() ? "" : String.format("*%s*", Type.format(a.getText()));

        return Field.of(title.toString(), text, true);
    }

    private static Field abilityField(Ability a) {
        StringBuilder title = new StringBuilder();
        title.append(String.format("[%s]", a.getType()));
        title.append(String.format(" %s", a.getName()));

        String text = a.getText().isBlank() ? "" : String.format("*%s*", Type.format(a.getText()));

        return Field.of(title.toString(), text, true);
    }

    public CardEmbed(DataCard card) {
        List<Field> fields = new ArrayList<>();

        if (card.getRules() != null)
            card.getRules().stream().map(s -> Field.of("Rule", s, true)).forEach(fields::add);
        if (card.getAbilities() != null)
            card.getAbilities().stream().map(CardEmbed::abilityField).forEach(fields::add);
        if (card.getAttacks() != null)
            card.getAttacks().stream().map(CardEmbed::attackField).forEach(fields::add);

        StringBuilder stats = new StringBuilder();
        stats.append(String.format("%s %s %s\n", card.getSet(), card.getNumber(), card.getRarity().getName()));

        if (card.getSupertype() == Supertype.POKEMON) {
            stats.append(String.format("HP: %d\n", card.getHp()));
            if (card.getWeaknesses() != null)
                for (Weakness weakness : card.getWeaknesses())
                    stats.append(
                            String.format("Weakness: %s %s\n", weakness.getType().getEmote(), weakness.getValue()));
            if (card.getResistances() != null)
                for (Resistance resistance : card.getResistances())
                    stats.append(
                            String.format("Resistance: %s %s\n", resistance.getType().getEmote(),
                                    resistance.getValue()));
            stats.append(String.format("Retreat Cost: %s\n", Type.toString(card.getRetreatCost())));
        }
        fields.add(Field.of("Stats", stats.toString(), false));

        builder.title(String.format("%s %s", card.getName(), Type.toString(card.getTypes())))
                .image(card.getImages().getLarge())
                .description(card.getSupertype().getName() + " "
                        + card.getSubtypes().stream().map(Subtype::getName).toList())
                .addAllFields(fields);
    }

    public EmbedCreateSpec build() {
        return builder.build();
    }
}
