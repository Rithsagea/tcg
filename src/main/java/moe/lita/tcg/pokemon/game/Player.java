package moe.lita.tcg.pokemon.game;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import moe.lita.tcg.pokemon.card.DataCard;

@Builder
@Data
public class Player {
    List<DataCard> deck;
    List<DataCard> hand;
}
