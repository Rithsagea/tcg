package moe.lita.tcg.discord.commands;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import moe.lita.tcg.discord.menus.DeckMenu;
import moe.lita.tcg.discord.menus.MenuManager;
import moe.lita.tcg.pokemon.Registry;
import moe.lita.tcg.pokemon.card.DataCard;
import moe.lita.tcg.pokemon.deck.Deck;
import reactor.core.publisher.Mono;

@Service
public class GetDeckCommand implements Command {

    @Autowired
    private Registry registry;

    @Autowired
    private MenuManager menuManager;

    @Override
    public Mono<Void> execute(ChatInputInteractionEvent event) {
        String id = event.getOption("id")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .get();

        Deck deck = registry.getDeck(id);
        List<DataCard> cards = deck.getCards().stream()
                .flatMap(card -> Collections.nCopies(card.getCount(), registry.getCard(card.getId())).stream())
                .toList();

        DeckMenu menu = DeckMenu.builder()
                .deck(deck)
                .cards(cards)
                .index(0)
                .build();

        return menuManager.createMenu(menu, event);
    }

}
