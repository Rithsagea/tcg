package moe.lita.tcg.discord.menus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import discord4j.core.event.domain.interaction.DeferrableInteractionEvent;
import discord4j.core.object.entity.Message;
import moe.lita.tcg.discord.commands.Command;
import reactor.core.publisher.Mono;

public interface Menu {
    Logger log = LoggerFactory.getLogger(Command.class);

    Mono<Message> update(DeferrableInteractionEvent event);

    default Mono<Void> handleError(Throwable error) {
        log.error("Unable to process " + getClass().getSimpleName(), error);
        return Mono.empty();
    }
}
