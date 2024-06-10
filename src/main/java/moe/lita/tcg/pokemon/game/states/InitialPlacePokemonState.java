package moe.lita.tcg.pokemon.game.states;

import java.util.List;

import moe.lita.tcg.pokemon.card.DataCard;
import moe.lita.tcg.pokemon.card.Subtype;
import moe.lita.tcg.pokemon.card.Supertype;
import moe.lita.tcg.pokemon.game.ActivePlayer;
import moe.lita.tcg.pokemon.game.Game;
import moe.lita.tcg.pokemon.game.Player;
import moe.lita.tcg.pokemon.game.actions.Action;

public class InitialPlacePokemonState extends State {

    public InitialPlacePokemonState(Game game) {
        super(game);
        this.actions.add(new PlaceActivePokemonAction(ActivePlayer.PLAYER1));
        this.actions.add(new PlaceActivePokemonAction(ActivePlayer.PLAYER2));
    }

    @Override
    public State advance() {
        if (game.getPlayer1().activePokemon == null || game.getPlayer2().activePokemon == null)
            return this;

        return this;
    }

    DataCard player1SelectedActivePokemon = null;
    DataCard player2SelectedActivePokemon = null;

    public class PlaceActivePokemonAction extends Action {
        public final ActivePlayer player;
        public final Player playerData;

        public PlaceActivePokemonAction(ActivePlayer player) {
            this.player = player;
            this.playerData = game.getPlayer(player);
        }

        @Override
        public boolean isEnabled() {
            return playerData.activePokemon == null
                    && playerData.hand.stream()
                            .filter(c -> c.getSupertype() == Supertype.POKEMON)
                            .filter(c -> c.getSubtypes().contains(Subtype.BASIC))
                            .findAny().isPresent();
        }

        @Override
        public boolean isComplete() {
            return null != switch (player) {
                case PLAYER1 -> player1SelectedActivePokemon;
                case PLAYER2 -> player2SelectedActivePokemon;
                default -> null;
            };
        }

        @Override
        public List<ActivePlayer> getUsers() {
            return List.of(player);
        }

        @Override
        public void apply(Game game) {
            if (playerData.activePokemon != null)
                playerData.hand.add(playerData.activePokemon);

            switch (player) {
                case PLAYER1:
                    playerData.activePokemon = player1SelectedActivePokemon;
                    player1SelectedActivePokemon = null;
                    break;
                case PLAYER2:
                    playerData.activePokemon = player2SelectedActivePokemon;
                    player2SelectedActivePokemon = null;
                    break;
                default:
            }
        }
    }

    // TODO PlaceBenchPokemonAction
    // TODO RemoveBenchPokemonAction
}
