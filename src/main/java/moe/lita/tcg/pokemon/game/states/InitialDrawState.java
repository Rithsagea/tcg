package moe.lita.tcg.pokemon.game.states;

import java.util.List;

import moe.lita.tcg.pokemon.game.ActivePlayer;
import moe.lita.tcg.pokemon.game.Game;
import moe.lita.tcg.pokemon.game.Player;
import moe.lita.tcg.pokemon.game.actions.Action;

public class InitialDrawState extends State {

    public InitialDrawState(Game game) {
        super(game);
        this.actions.add(new DrawAction(ActivePlayer.PLAYER1));
        this.actions.add(new DrawAction(ActivePlayer.PLAYER2));
    }

    @Override
    public State advance() {
        if (player1Drawn && player2Drawn)
            return new InitialPlacePokemonState(game);
        return this;
    }

    boolean player1Drawn = false;
    boolean player2Drawn = false;

    public class DrawAction extends Action {
        public final ActivePlayer player;

        public DrawAction(ActivePlayer player) {
            this.player = player;
        }

        @Override
        public boolean isEnabled() {
            return switch (player) {
                case ActivePlayer.PLAYER1 -> !player1Drawn;
                case ActivePlayer.PLAYER2 -> !player2Drawn;
                default -> false;
            };
        }

        @Override
        public boolean isComplete() {
            return true;
        }

        @Override
        public List<ActivePlayer> getUsers() {
            return List.of(player);
        }

        @Override
        public void apply(Game game) {
            Player player = game.getPlayer(this.player);
            for (int i = 0; i < 7; i++) {
                player.hand.add(player.deck.removeLast());
            }
        }
    }

    // TODO MulliganAction
}
