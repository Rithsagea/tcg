package moe.lita.tcg.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TcgCard {
    @Id
    Long id;
    String card;
    Long user;
}
