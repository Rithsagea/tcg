package moe.lita.tcg.discord.commands;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import discord4j.core.event.domain.interaction.ApplicationCommandInteractionEvent;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import moe.lita.tcg.pokemon.Database;
import moe.lita.tcg.pokemon.card.Card;
import reactor.core.publisher.Mono;

@Service
public class GetCardCommand implements Command {

    @Autowired
    private Database database;

    @Override
    public Mono<Void> execute(ApplicationCommandInteractionEvent event) {
        switch (event) {
            case ChatInputInteractionEvent c:
                Optional<Card> card = c.getOption("set")
                        .flatMap(ApplicationCommandInteractionOption::getValue)
                        .map(ApplicationCommandInteractionOptionValue::asString)
                        .map(database::getSet)
                        .map(set -> set.get((int) (Math.random() * set.size())));
                if (!card.isPresent())
                    return c.reply("No card found!");

                return c.reply(card.get().getImages().getLarge());
            default:
                return Mono.empty();
        }
    }
}
