package moe.lita.tcg.pokemon.game.states;

import java.util.List;

import lombok.experimental.SuperBuilder;
import moe.lita.tcg.pokemon.game.Game;
import moe.lita.tcg.pokemon.game.actions.Action;

@SuperBuilder
public abstract class State {

    final Game game;

    /*
     * Gets a list of available actions
     */
    public abstract List<Action> getActions();

    /*
     * Returns the next state after an action has been executed
     */
    public abstract State advance();
}
