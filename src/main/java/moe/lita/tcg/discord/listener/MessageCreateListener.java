package moe.lita.tcg.discord.listener;

import org.springframework.stereotype.Component;

import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

@Component
public class MessageCreateListener implements EventListener<MessageCreateEvent> {

    @Override
    public Class<MessageCreateEvent> getEventType() {
        return MessageCreateEvent.class;
    }

    @Override
    public Mono<Void> execute(MessageCreateEvent event) {
        // log.info("Received message: {}", event.toString());
        return Mono.empty();
    }

}
