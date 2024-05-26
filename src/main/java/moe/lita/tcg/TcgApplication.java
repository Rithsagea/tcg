package moe.lita.tcg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import discord4j.core.GatewayDiscordClient;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class TcgApplication {
	public static void main(String[] args) {
		ApplicationContext app = SpringApplication.run(TcgApplication.class, args);
		app.getBean(GatewayDiscordClient.class).onDisconnect().block();
	}
}
