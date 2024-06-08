package moe.lita.tcg.pokemon.game.actions;

import java.util.List;

import moe.lita.tcg.pokemon.game.ActivePlayer;
import moe.lita.tcg.pokemon.game.Game;

public abstract class Action {

    /*
     * Returns if this action is currently enabled
     */
    public abstract boolean isEnabled();

    /*
     * Returns if all of the options for this action have been
     * filled.
     */
    public abstract boolean isComplete();

    /*
     * Returns a list of users who can use this action
     */
    public abstract List<ActivePlayer> getUsers();

    /*
     * Performs this action on the given game
     * Should only be called if `isComplete()` return true.
     */
    public abstract void apply(Game game);
}
