package moe.lita.tcg.pokemon.game.actions;

import moe.lita.tcg.pokemon.game.ActivePlayer;
import moe.lita.tcg.pokemon.game.Game;

public interface Action {
    /*
     * Returns if all of the options for this action have been
     * filled.
     */
    public boolean isComplete();

    /*
     * Performs this action on the given game
     * Should only be called if `isComplete()` return true.
     */
    public void apply(Game game);

    /*
     * Returns the player that can initiate this action
     */
    public ActivePlayer getPlayer();
}
