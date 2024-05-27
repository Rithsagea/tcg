package moe.lita.tcg.discord.menus;

import java.util.ArrayList;
import java.util.List;

import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import discord4j.core.event.domain.interaction.DeferrableInteractionEvent;
import discord4j.core.event.domain.interaction.SelectMenuInteractionEvent;
import discord4j.core.object.component.ActionRow;
import discord4j.core.object.component.Button;
import discord4j.core.object.component.SelectMenu;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.core.spec.InteractionReplyEditMono;
import lombok.Builder;
import moe.lita.tcg.discord.embeds.DeckEmbedBuilder;
import moe.lita.tcg.pokemon.card.DataCard;
import moe.lita.tcg.pokemon.deck.Deck;

@Builder
public class DeckMenu implements Menu {

    Deck deck;
    List<DataCard> cards;
    int index;

    @Override
    public InteractionReplyEditMono update(DeferrableInteractionEvent event) {
        switch (event) {
            case ButtonInteractionEvent b:
                switch (b.getCustomId()) {
                    case "prev":
                        index--;
                        break;
                    case "next":
                        index++;
                        break;
                }
                break;

            case SelectMenuInteractionEvent s:
                index = Integer.parseInt(s.getValues().get(0));

            default:
        }

        EmbedCreateSpec embed = DeckEmbedBuilder.of(deck, cards.get(index), index).build();

        List<SelectMenu.Option> options = new ArrayList<>();
        for (int i = index - 12; i <= index + 12; i++) {
            if (i < 0 || i >= 60)
                continue;
            DataCard card = cards.get(i);
            String cardLine = String.format("%d - %s", i + 1, card.toLine());
            SelectMenu.Option option = SelectMenu.Option.of(cardLine, Integer.toString(i))
                    .withDefault(i == index);
            if (card.getTypes() != null && card.getTypes().size() > 0)
                option = option.withEmoji(card.getTypes().get(0).getEmoji());

            options.add(option);
        }

        return event.editReply("")
                .withEmbeds(embed)
                .withComponents(
                        ActionRow.of(
                                SelectMenu.of("cards", options)),
                        ActionRow.of(
                                Button.primary("prev", "Prev").disabled(index <= 0),
                                Button.primary("next", "Next").disabled(index > 60)));
    }

}
