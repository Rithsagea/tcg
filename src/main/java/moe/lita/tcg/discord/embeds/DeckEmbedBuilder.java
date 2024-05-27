package moe.lita.tcg.discord.embeds;

import discord4j.core.spec.EmbedCreateSpec;
import moe.lita.tcg.pokemon.card.DataCard;
import moe.lita.tcg.pokemon.card.Type;
import moe.lita.tcg.pokemon.deck.Deck;

public class DeckEmbedBuilder {
    public static EmbedCreateSpec.Builder of(Deck deck, DataCard card, int index) {
        return CardEmbedBuilder.of(card)
                .title(String.format("[%s %s] [%d/%d] %s %s",
                        deck.getName(), Type.toString(deck.getTypes()),
                        index + 1, 60,
                        card.getName(),
                        Type.toString(card.getTypes())));
    }
}
