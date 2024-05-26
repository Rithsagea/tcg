package moe.lita.tcg.pokemon;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import moe.lita.tcg.pokemon.card.BaseCard;
import moe.lita.tcg.pokemon.card.DataCard;

@Component
@Log4j2
public class Database {

    private ObjectMapper objectMapper = new ObjectMapper();
    private Map<String, DataCard> cards = new HashMap<>();
    private Map<String, List<DataCard>> sets = new HashMap<>();

    private static String[] CARD_SETS = {
            "bwp",
            "bw1",
            "bw2",
            "bw3",
            "bw4",
            "bw5",
            "bw6",
            "bw7",
            "bw8",
            "bw9",
            "bw10",
            "bw11",
    };

    @PostConstruct
    public void init() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        for (String set : CARD_SETS) {
            InputStream resourceStream = classLoader.getResourceAsStream(String.format("sets/%s.json", set));
            List<DataCard> data = objectMapper.readValue(resourceStream, new TypeReference<List<BaseCard>>() {})
                    .stream()
                    .map(card -> DataCard
                            .toBuilder(card)
                            .set(set)
                            .build())
                    .collect(Collectors.toList());

            log.info("Loaded {} cards from {}", data.size(), set);
            sets.put(set, data);
            data.forEach(c -> this.cards.put(c.getId(), c));
        }
    }

    public DataCard getCard(String id) {
        return cards.get(id);
    }

    public List<DataCard> getSet(String id) {
        return sets.get(id);
    }
}
