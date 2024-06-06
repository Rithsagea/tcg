package moe.lita.tcg.pokemon.game.states;

import java.util.List;

import lombok.experimental.SuperBuilder;
import moe.lita.tcg.pokemon.game.actions.Action;

@SuperBuilder
public class InitialDrawState extends State {

    @Override
    public List<Action> getActions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getActions'");
    }

    @Override
    public State advance() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'advance'");
    }

}
