package moe.lita.tcg.pokemon.card;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Legality {
    @RequiredArgsConstructor
    public static enum State {
        LEGAL("Legal"), BANNED("Banned");

        @Getter
        @JsonValue
        private final String name;
    }

    Legality.State standard;
    Legality.State expanded;
    Legality.State unlimited;
}
