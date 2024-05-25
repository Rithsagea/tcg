package moe.lita.tcg.pokemon;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import moe.lita.tcg.pokemon.card.Card;

@Component
@Log4j2
public class Database {

    private ObjectMapper objectMapper = new ObjectMapper();
    private Map<String, Card> cards = new HashMap<>();
    private Map<String, List<Card>> sets = new HashMap<>();

    private static String[] CARD_SETS = {
            "bwp",
            "bw1",
            "bw2",
            "bw3",
            "bw4",
            "bw5",
            "bw6",
    };

    @PostConstruct
    public void init() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        for (String set : CARD_SETS) {
            InputStream resourceStream = classLoader.getResourceAsStream(String.format("sets/%s.json", set));
            List<Card> data = objectMapper.readValue(resourceStream, new TypeReference<List<Card>>() {});
            log.info("Loaded {} cards from {}", data.size(), set);
            sets.put(set, data);
            data.forEach(c -> this.cards.put(c.getId(), c));
        }
    }

    public Card getCard(String id) {
        return cards.get(id);
    }

    public List<Card> getSet(String id) {
        return sets.get(id);
    }
}
