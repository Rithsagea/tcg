package moe.lita.tcg.discord.commands;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import discord4j.core.event.domain.interaction.ApplicationCommandInteractionEvent;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import moe.lita.tcg.discord.embeds.CardEmbed;
import moe.lita.tcg.pokemon.Registry;
import moe.lita.tcg.pokemon.card.DataCard;
import reactor.core.publisher.Mono;

@Service
public class GetCardCommand implements Command {

    @Autowired
    private Registry registry;

    @Override
    public Mono<Void> execute(ApplicationCommandInteractionEvent event) {
        switch (event) {
            case ChatInputInteractionEvent c:
                String set = c.getOption("set")
                        .flatMap(ApplicationCommandInteractionOption::getValue)
                        .map(ApplicationCommandInteractionOptionValue::asString)
                        .get();

                String number = c.getOption("number")
                        .flatMap(ApplicationCommandInteractionOption::getValue)
                        .map(ApplicationCommandInteractionOptionValue::asString)
                        .get();

                Optional<DataCard> card = registry.getSet(set).stream()
                        .filter(a -> a.getNumber().equals(number))
                        .findFirst();

                if (!card.isPresent())
                    return c.reply("No card found!");

                return c.reply("").withEmbeds(new CardEmbed(card.get()).build());
            default:
                return Mono.empty();
        }
    }
}
