package moe.lita.tcg.pokemon.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import moe.lita.tcg.pokemon.Registry;
import moe.lita.tcg.pokemon.card.DataCard;

@SpringBootTest
public class GameTest {

    @Autowired
    private Registry registry;

    private List<DataCard> constructDeck(String... cardIds) {
        return Stream.of(cardIds)
                .map(registry::getCard)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
