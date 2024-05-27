package moe.lita.tcg.discord.listener;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import jakarta.annotation.PostConstruct;
import moe.lita.tcg.discord.commands.Command;
import reactor.core.publisher.Mono;

@Service
public class ChatInputInteractionListener implements EventListener<ChatInputInteractionEvent> {

    @Autowired
    private List<Command> commands;

    private Map<String, Command> commandMap;

    @PostConstruct
    public void init() {
        commandMap = commands.stream().collect(Collectors.toMap(cmd -> cmd.getData().name(), Function.identity()));
    }

    @Override
    public Class<ChatInputInteractionEvent> getEventType() {
        return ChatInputInteractionEvent.class;
    }

    @Override
    public Mono<Void> execute(ChatInputInteractionEvent event) {
        log.info("Received command {}", event.getCommandName());

        Command command = commandMap.get(event.getCommandName());

        return Mono.just(event)
                .flatMap(command::execute)
                .onErrorResume(command::handleError);
    }

}
