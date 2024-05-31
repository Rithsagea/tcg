package moe.lita.tcg.discord.commands;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import moe.lita.tcg.discord.embeds.CardEmbedBuilder;
import moe.lita.tcg.pokemon.Registry;
import moe.lita.tcg.pokemon.card.DataCard;
import reactor.core.publisher.Mono;

@Component
public class GetCardCommand implements Command {

    @Autowired
    private Registry registry;

    @Override
    public Mono<Void> execute(ChatInputInteractionEvent event) {
        String set = event.getOption("set")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .get();

        String number = event.getOption("number")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .get();

        Optional<DataCard> card = registry.getSet(set).stream()
                .filter(a -> a.getNumber().equals(number))
                .findFirst();

        if (!card.isPresent())
            return event.reply("No card found!");

        return event.reply("").withEmbeds(CardEmbedBuilder.of(card.get()).build());
    }
}
