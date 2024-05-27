package moe.lita.tcg.discord.commands;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import discord4j.common.JacksonResources;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.discordjson.json.ApplicationCommandRequest;
import lombok.SneakyThrows;
import reactor.core.publisher.Mono;

public interface Command {

    JacksonResources d4jMapper = JacksonResources.create();
    Logger log = LoggerFactory.getLogger(Command.class);

    Mono<Void> execute(ChatInputInteractionEvent event);

    @SneakyThrows(IOException.class)
    default ApplicationCommandRequest getData() {
        Class<? extends Command> clazz = this.getClass();
        return d4jMapper.getObjectMapper().readValue(
                clazz.getClassLoader().getResourceAsStream(String.format("commands/%s.json", clazz.getSimpleName())),
                ApplicationCommandRequest.class);
    }

    default Mono<Void> handleError(Throwable error) {
        log.error("Unable to process " + getClass().getSimpleName(), error);
        return Mono.empty();
    }
}
