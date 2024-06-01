package moe.lita.tcg.pokemon.game;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class Game {
    @NonNull
    Player player1;
    @NonNull
    Player player2;

    ActivePlayer activePlayer;

    State state;
}
