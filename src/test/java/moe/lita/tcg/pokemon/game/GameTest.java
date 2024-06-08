package moe.lita.tcg.pokemon.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import moe.lita.tcg.pokemon.Registry;
import moe.lita.tcg.pokemon.card.DataCard;
import moe.lita.tcg.pokemon.game.states.InitialDrawState;
import moe.lita.tcg.pokemon.game.states.InitialDrawState.DrawAction;
import moe.lita.tcg.pokemon.game.states.InitialState;
import moe.lita.tcg.pokemon.game.states.InitialState.CoinflipAction;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class GameTest {

    @Autowired
    private Registry registry;

    private List<DataCard> constructDeck(List<String> cardIds) {
        return cardIds.stream()
                .map(registry::getCard)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Player player1;
    private Player player2;
    private Game game;

    private List<String> deck1 = List.of(
            // Serperior
            "bw1-5",
            "bw1-3",
            "bw1-1",

            // Grass Energy
            "hgss1-115",
            "hgss1-115",
            "hgss1-115",
            "hgss1-115");

    private List<String> deck2 = List.of(
            // Emboar
            "bw1-19",
            "bw1-17",
            "bw1-15",

            // Fire Energy
            "bw1-106",
            "bw1-106",
            "bw1-106",
            "bw1-106");

    @Mock
    private Random mockRand;

    @BeforeAll
    public void init() {
        player1 = Player.builder()
                .deck(constructDeck(deck1))
                .build();

        player2 = Player.builder()
                .deck(constructDeck(deck2))
                .build();

        game = Game.builder()
                .player1(player1)
                .player2(player2)
                .build();

        game.state = new InitialState(game);
    }

    @Test
    @Order(1)
    public void testCoinflip() {
        assertTrue(game.getActivePlayer() == null);

        var action = (CoinflipAction) game.getActions().get(0);

        when(mockRand.nextDouble()).thenReturn(0.1);
        action.flipCoin(mockRand);
        assertTrue(action.isComplete());

        game.applyAction(action);
        assertEquals(ActivePlayer.PLAYER1, game.getActivePlayer());
        assertInstanceOf(InitialDrawState.class, game.state);
    }

    @Test
    @Order(2)
    public void testDraw() {
        var p1Draw = (DrawAction) game.getActions().get(0);
        var p2Draw = (DrawAction) game.getActions().get(1);

        var player1ExpectedHand = new ArrayList<>(game.player1.deck.subList(deck1.size() - 7, deck1.size()));
        Collections.reverse(player1ExpectedHand);
        assertEquals(Collections.emptyList(), game.player1.hand);
        game.applyAction(p1Draw);
        assertEquals(player1ExpectedHand, game.player1.hand);
        assertEquals(deck1.size() - 7, game.player1.deck.size());

        var player2ExpectedHand = new ArrayList<>(game.player2.deck.subList(deck2.size() - 7, deck2.size()));
        Collections.reverse(player1ExpectedHand);
        assertEquals(Collections.emptyList(), game.player2.hand);
        game.applyAction(p2Draw);
        assertEquals(player2ExpectedHand, game.player2.hand);
        assertEquals(deck2.size() - 7, game.player2.deck.size());
    }

}
