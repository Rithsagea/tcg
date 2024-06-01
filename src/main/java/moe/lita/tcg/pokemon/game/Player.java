package moe.lita.tcg.pokemon.game;

import java.util.List;

import lombok.Data;
import moe.lita.tcg.pokemon.card.DataCard;

@Data
public class Player {
    List<DataCard> deck;
    List<DataCard> hand;
}
