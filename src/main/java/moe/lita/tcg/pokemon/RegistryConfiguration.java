package moe.lita.tcg.pokemon;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RegistryConfiguration {
    Registry registry() {
        Registry registry = new Registry();

        return registry;
    }
}
