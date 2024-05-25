package moe.lita.tcg.pokemon;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {
    Database database() {
        Database database = new Database();

        return database;
    }
}
