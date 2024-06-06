package moe.lita.tcg.pokemon.game.states;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.experimental.SuperBuilder;
import moe.lita.tcg.pokemon.game.ActivePlayer;
import moe.lita.tcg.pokemon.game.Game;
import moe.lita.tcg.pokemon.game.actions.Action;

@SuperBuilder
public class InitialState extends State {

    public static class CoinflipAction implements Action {

        ActivePlayer winner = null;

        public ActivePlayer flipCoin(Random rand) {
            return winner = rand.nextDouble() < 0.5 ? ActivePlayer.PLAYER1 : ActivePlayer.PLAYER2;
        }

        @Override
        public boolean isComplete() {
            return winner != null;
        }

        @Override
        public void apply(Game game) {
            game.setActivePlayer(winner);
        }

        @Override
        public ActivePlayer getPlayer() {
            return null;
        }

    }

    @Override
    public List<Action> getActions() {
        List<Action> actions = new ArrayList<>();
        if (game.getActivePlayer() == null)
            actions.add(new CoinflipAction());

        return actions;
    }

    @Override
    public State advance() {
        if (!getActions().isEmpty())
            return this;
        return InitialDrawState.builder().game(game).build();
    }

}
