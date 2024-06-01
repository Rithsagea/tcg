package moe.lita.tcg.pokemon.game;

public interface Action {
    /*
     * Returns if all of the options for this action have been
     * filled.
     */
    public boolean isComplete();

    /*
     * Performs this action from the perspective of the active player.
     * Should only be called if `isComplete()` return true.
     */
    public void apply(Game game, ActivePlayer player);

    /*
     * Returns the player that can initiate this action
     */
    public ActivePlayer getPlayer();
}
