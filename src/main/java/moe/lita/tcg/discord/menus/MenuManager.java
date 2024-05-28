package moe.lita.tcg.discord.menus;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.event.domain.interaction.ComponentInteractionEvent;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Service
@Log4j2
public class MenuManager {
    private Cache<Snowflake, Menu> cache = CacheBuilder.newBuilder()
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .build();

    public Mono<Void> createMenu(Menu menu, ChatInputInteractionEvent event) {
        return event.reply("Loading...")
                .then(menu.update(event))
                .doOnNext(msg -> {
                    log.info("Created Menu: " + msg.getId());
                    cache.put(msg.getId(), menu);
                })
                .then();
    }

    public Mono<Void> handle(ComponentInteractionEvent event) {
        Menu menu = cache.getIfPresent(event.getMessageId());
        if (menu == null)
            return event.reply("This menu has expired!").withEphemeral(true);

        log.info("Handling {}: {}", menu.getClass().getSimpleName(), event.getCustomId());
        return event.deferEdit()
                .then(menu.update(event))
                .then();

    }
}
