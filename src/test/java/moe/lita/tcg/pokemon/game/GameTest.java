package moe.lita.tcg.pokemon.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import moe.lita.tcg.pokemon.Registry;
import moe.lita.tcg.pokemon.card.DataCard;
import moe.lita.tcg.pokemon.game.states.InitialDrawState;
import moe.lita.tcg.pokemon.game.states.InitialState;
import moe.lita.tcg.pokemon.game.states.InitialState.CoinflipAction;

@SpringBootTest
public class GameTest {

    @Autowired
    private Registry registry;

    private List<DataCard> constructDeck(String... cardIds) {
        return Stream.of(cardIds)
                .map(registry::getCard)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Player player1;
    private Player player2;
    private Game game;

    @Mock
    private Random mockRand;

    public GameTest() {
        player1 = Player.builder().build();
        player2 = Player.builder().build();
        game = Game.builder()
                .player1(player1)
                .player2(player2)
                .build();

        game.state = InitialState.builder()
                .game(game)
                .build();
    }

    @Test
    public void testCoinflip() {
        assertTrue(game.getActivePlayer() == null);

        var state = game.state;
        var action = (CoinflipAction) state.getActions().get(0);

        when(mockRand.nextDouble()).thenReturn(0.1);
        action.flipCoin(mockRand);
        assertTrue(action.isComplete());

        action.apply(game);
        assertEquals(ActivePlayer.PLAYER1, game.getActivePlayer());

        game.state = state.advance();
        assertInstanceOf(InitialDrawState.class, game.state);
    }

}
