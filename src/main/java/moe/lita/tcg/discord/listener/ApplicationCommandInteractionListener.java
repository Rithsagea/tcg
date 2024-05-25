package moe.lita.tcg.discord.listener;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import discord4j.core.event.domain.interaction.ApplicationCommandInteractionEvent;
import jakarta.annotation.PostConstruct;
import moe.lita.tcg.discord.commands.Command;
import reactor.core.publisher.Mono;

@Service
public class ApplicationCommandInteractionListener implements EventListener<ApplicationCommandInteractionEvent> {

    @Autowired
    private List<Command> commands;

    private Map<String, Command> commandMap;

    @PostConstruct
    public void init() {
        commandMap = commands.stream().collect(Collectors.toMap(cmd -> cmd.getData().name(), Function.identity()));
    }

    @Override
    public Class<ApplicationCommandInteractionEvent> getEventType() {
        return ApplicationCommandInteractionEvent.class;
    }

    @Override
    public Mono<Void> execute(ApplicationCommandInteractionEvent event) {
        log.info("Received command {}", event.getCommandName());

        return Mono.just(event)
                .flatMap(cmd -> commandMap.get(event.getCommandName()).execute(event))
                .doOnError(e -> log.error("Error executing command", e))
                .onErrorResume(e -> event.reply("Error executing command").withEphemeral(true));
    }

}
