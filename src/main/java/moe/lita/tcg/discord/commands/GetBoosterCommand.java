package moe.lita.tcg.discord.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import discord4j.core.event.domain.interaction.ApplicationCommandInteractionEvent;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import moe.lita.tcg.discord.embeds.CardEmbed;
import moe.lita.tcg.pokemon.Database;
import moe.lita.tcg.pokemon.card.DataCard;
import moe.lita.tcg.pokemon.card.Supertype;
import reactor.core.publisher.Mono;

@Service
public class GetBoosterCommand implements Command {

    @Autowired
    private Database database;

    private List<DataCard> getBooster(List<DataCard> cards) {
        List<DataCard> draw = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 10; i++)
            draw.add(cards.get(rand.nextInt(cards.size())));

        return draw;
    }

    @Override
    public Mono<Void> execute(ApplicationCommandInteractionEvent event) {
        switch (event) {
            case ChatInputInteractionEvent c:
                String set = c.getOption("set")
                        .flatMap(ApplicationCommandInteractionOption::getValue)
                        .map(ApplicationCommandInteractionOptionValue::asString)
                        .get();

                List<DataCard> draw = getBooster(database.getSet(set));

                return c.reply()
                        .withEmbeds(draw.stream()
                                .map(CardEmbed::new)
                                .map(CardEmbed::build)
                                .toList());
            default:
                return Mono.empty();
        }
    }

}
