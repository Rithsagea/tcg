package moe.lita.tcg.discord.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.spec.EmbedCreateSpec;
import moe.lita.tcg.discord.embeds.CardEmbedBuilder;
import moe.lita.tcg.pokemon.Registry;
import moe.lita.tcg.pokemon.card.DataCard;
import reactor.core.publisher.Mono;

@Component
public class GetBoosterCommand implements Command {

    @Autowired
    private Registry registry;

    private List<DataCard> getBooster(List<DataCard> cards) {
        List<DataCard> draw = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 10; i++)
            draw.add(cards.get(rand.nextInt(cards.size())));

        return draw;
    }

    @Override
    public Mono<Void> execute(ChatInputInteractionEvent event) {
        String set = event.getOption("set")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .get();

        List<DataCard> draw = getBooster(registry.getSet(set));

        return event.reply()
                .withEmbeds(draw.stream()
                        .map(CardEmbedBuilder::of)
                        .map(EmbedCreateSpec.Builder::build)
                        .toList());
    }

}
