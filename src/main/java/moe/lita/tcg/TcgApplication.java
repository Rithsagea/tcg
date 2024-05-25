package moe.lita.tcg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import discord4j.core.GatewayDiscordClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import moe.lita.tcg.pokemon.Database;

@SpringBootApplication
@RequiredArgsConstructor
public class TcgApplication {

	final Database db;
	final GatewayDiscordClient discord;

	@PostConstruct
	void init() {
		discord.onDisconnect().block();
	}

	public static void main(String[] args) {
		SpringApplication.run(TcgApplication.class, args);
	}

}
