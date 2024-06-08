package moe.lita.tcg.pokemon.game.states;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.RequiredArgsConstructor;
import moe.lita.tcg.pokemon.game.Game;
import moe.lita.tcg.pokemon.game.actions.Action;

@RequiredArgsConstructor
public abstract class State {

    public final Game game;
    protected final List<Action> actions = new ArrayList<>();

    /*
     * Gets a list of available actions
     */
    public final List<Action> getActions() {
        return Collections.unmodifiableList(actions);
    }

    /*
     * Returns the next state after an action has been executed
     */
    public abstract State advance();
}
