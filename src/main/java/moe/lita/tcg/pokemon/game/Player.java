package moe.lita.tcg.pokemon.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Builder;
import lombok.Builder.Default;
import moe.lita.tcg.pokemon.card.DataCard;

@Builder
public class Player {
    public List<DataCard> deck;

    @Default
    public List<DataCard> hand = new ArrayList<>();

    @Default
    public DataCard activePokemon = null;

    @Default
    public List<DataCard> bench = Arrays.asList(new DataCard[6]);
}
