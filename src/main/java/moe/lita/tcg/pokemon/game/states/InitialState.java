package moe.lita.tcg.pokemon.game.states;

import java.util.List;
import java.util.Random;

import moe.lita.tcg.pokemon.game.ActivePlayer;
import moe.lita.tcg.pokemon.game.Game;
import moe.lita.tcg.pokemon.game.actions.Action;

public class InitialState extends State {

    public InitialState(Game game) {
        super(game);

        this.actions.add(new CoinflipAction());
    }

    @Override
    public State advance() {
        if (!flippedCoin)
            return this;
        return new InitialDrawState(game);
    }

    ActivePlayer winner = null;
    boolean flippedCoin = false;

    public class CoinflipAction extends Action {

        public ActivePlayer flipCoin(Random rand) {
            return winner = rand.nextDouble() < 0.5 ? ActivePlayer.PLAYER1 : ActivePlayer.PLAYER2;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        @Override
        public boolean isComplete() {
            return winner != null;
        }

        @Override
        public void apply(Game game) {
            flippedCoin = true;
            game.setActivePlayer(winner);
        }

        @Override
        public List<ActivePlayer> getUsers() {
            return List.of(ActivePlayer.SYSTEM);
        }

    }
}
