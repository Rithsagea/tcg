package moe.lita.tcg.pokemon.game;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import moe.lita.tcg.pokemon.game.actions.Action;
import moe.lita.tcg.pokemon.game.states.State;

@Builder
@Data
public class Game {
    @NonNull
    Player player1;
    @NonNull
    Player player2;

    ActivePlayer activePlayer;
    State state;

    public Player getPlayer(ActivePlayer activePlayer) {
        return switch (activePlayer) {
            case PLAYER1 -> player1;
            case PLAYER2 -> player2;
            default -> null;
        };
    }

    public List<Action> getActions() {
        return state.getActions();
    }

    public boolean applyAction(Action action) {
        if (!action.isEnabled() || !action.isComplete())
            return false;

        action.apply(this);
        state = state.advance();

        return true;
    }
}
