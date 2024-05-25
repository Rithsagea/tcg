package moe.lita.tcg.discord;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.rest.service.ApplicationService;
import lombok.extern.log4j.Log4j2;
import moe.lita.tcg.discord.commands.Command;
import moe.lita.tcg.discord.listener.EventListener;

@Log4j2
@Configuration
public class DiscordConfiguration {

    @Value("${token}")
    private String token;

    @Value("${testGuild}")
    private Long testGuildId;

    @Bean
    <T extends Event> GatewayDiscordClient discordClient(
            List<EventListener<T>> eventListeners,
            List<Command> commands) {
        GatewayDiscordClient client = null;
        try {
            client = DiscordClientBuilder.create(token)
                    .build()
                    .login()
                    .block();

            for (EventListener<T> listener : eventListeners) {
                client.on(listener.getEventType())
                        .flatMap(listener::execute)
                        .onErrorResume(listener::handleError)
                        .subscribe();
            }

            long applicationId = client.getRestClient().getApplicationId().block();
            ApplicationService applicationService = client.getRestClient().getApplicationService();
            List<ApplicationCommandRequest> commandRequests = commands.stream().map(Command::getData)
                    .collect(Collectors.toList());

            applicationService.bulkOverwriteGuildApplicationCommand(applicationId, testGuildId, commandRequests)
                    .doOnNext(cmd -> log.info("Registered guild command {}", cmd.name()))
                    .doOnError(e -> log.error("Failed to register guild commands", e))
                    .subscribe();

            // Map<String, ApplicationCommandData> discordCommands = applicationService
            // .getGuildApplicationCommands(applicationId, testGuildId)
            // .collectMap(ApplicationCommandData::name)
            // .block();

        } catch (Exception exception) {
            log.error("Error initializing discord client", exception);
        }

        return client;
    }
}
