package moe.lita.tcg.discord.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import discord4j.core.event.domain.interaction.SelectMenuInteractionEvent;
import moe.lita.tcg.discord.menus.MenuManager;
import reactor.core.publisher.Mono;

@Service
public class SelectMenuInteractionListener implements EventListener<SelectMenuInteractionEvent> {

    @Autowired
    private MenuManager menuManager;

    @Override
    public Class<SelectMenuInteractionEvent> getEventType() {
        return SelectMenuInteractionEvent.class;
    }

    @Override
    public Mono<Void> execute(SelectMenuInteractionEvent event) {
        return menuManager.handle(event);
    }

}
