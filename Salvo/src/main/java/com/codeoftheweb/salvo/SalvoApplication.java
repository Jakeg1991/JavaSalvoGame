package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository) {
		return (args) -> {

			Player player1 = new Player( "Jack Bauer", "24", "j.bauer@ctu.gov");
			Player player2 = new Player( "Chloe O'Brian", "42", "c.obrian@ctu.gov");
			Player player3 = new Player( "Kim Bauer", "kb", "kim_bauer@gmail.com");
			Player player4 = new Player( "Tony Almeida", "mole", "t.almeida@ctu.gov");


			Date date = new Date();
			Date date2 = Date.from(date.toInstant().plusSeconds(1800));
			Date date3 = Date.from(date.toInstant().plusSeconds(3600));
			Date date4 = Date.from(date.toInstant().plusSeconds(5400));
			Game game1 = new Game(date);
			Game game2 = new Game(date2);
			Game game3 = new Game(date3);
			Game game4 = new Game(date4);

			GamePlayer gp1 = new GamePlayer(date2);
			GamePlayer gp2 = new GamePlayer(date2);

			player1.addGamePlayer(gp1);
			player2.addGamePlayer(gp2);

			game1.addGamePlayer(gp1);
			game1.addGamePlayer(gp2);

			gameRepository.save(game1);
			gameRepository.save(game2);
			gameRepository.save(game3);
			gameRepository.save(game4);

			playerRepository.save(player1);
			playerRepository.save(player2);
			playerRepository.save(player3);
			playerRepository.save(player4);

			gamePlayerRepository.save(gp1);
			gamePlayerRepository.save(gp2);
		};
	}
}