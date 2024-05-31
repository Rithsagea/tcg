package moe.lita.tcg.discord.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import moe.lita.tcg.discord.menus.MenuManager;
import reactor.core.publisher.Mono;

@Component
public class ButtonInteractionListener implements EventListener<ButtonInteractionEvent> {

    @Autowired
    private MenuManager menuManager;

    @Override
    public Class<ButtonInteractionEvent> getEventType() {
        return ButtonInteractionEvent.class;
    }

    @Override
    public Mono<Void> execute(ButtonInteractionEvent event) {
        return menuManager.handle(event);
    }

}
