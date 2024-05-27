package moe.lita.tcg.pokemon;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import moe.lita.tcg.pokemon.card.BaseCard;
import moe.lita.tcg.pokemon.card.DataCard;
import moe.lita.tcg.pokemon.deck.Deck;

@Component
@Log4j2
public class Registry {

    private ObjectMapper objectMapper = new ObjectMapper();
    private Map<String, DataCard> cards = new HashMap<>();
    private Map<String, List<DataCard>> sets = new HashMap<>();

    private Map<String, Deck> decks = new HashMap<>();

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() throws IOException {
        for (Resource r : applicationContext.getResources("classpath:sets/*.json")) {
            InputStream resourceStream = r.getInputStream();
            String set = r.getFilename().split("\\.")[0];
            List<DataCard> data = objectMapper.readValue(resourceStream, new TypeReference<List<BaseCard>>() {})
                    .stream()
                    .map(card -> DataCard
                            .toBuilder(card)
                            .set(set)
                            .build())
                    .collect(Collectors.toList());

            log.info("Loaded {} cards from set {}", data.size(), set);
            sets.put(set, data);
            data.forEach(c -> this.cards.put(c.getId(), c));
        }

        for (Resource r : applicationContext.getResources("classpath:decks/*.json")) {
            InputStream resourceStream = r.getInputStream();
            String deck = r.getFilename().split("\\.")[0];
            List<Deck> data = objectMapper.readValue(resourceStream, new TypeReference<List<Deck>>() {});

            log.info("Loaded {} decks from {}", data.size(), deck);
            data.forEach(c -> this.decks.put(c.getId(), c));
        }
    }

    public DataCard getCard(String id) {
        return cards.get(id);
    }

    public List<DataCard> getSet(String id) {
        return sets.get(id);
    }

    public Deck getDeck(String id) {
        return decks.get(id);
    }
}
