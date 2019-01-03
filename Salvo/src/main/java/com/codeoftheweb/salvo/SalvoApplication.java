package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository,
									  GameRepository gameRepository,
									  GamePlayerRepository gamePlayerRepository,
									  ShipRepository shipRepository) {
		return (args) -> {

			Player player1 = new Player( "Jack Bauer", "24", "j.bauer@ctu.gov");
			Player player2 = new Player( "Chloe O'Brian", "42", "c.obrian@ctu.gov");
			Player player3 = new Player( "Kim Bauer", "kb", "kim_bauer@gmail.com");
			Player player4 = new Player( "Tony Almeida", "mole", "t.almeida@ctu.gov");
			Player player5 = new Player( "Jake Godwin", "ct401", "jakegodwin@rocketmail.com");

			playerRepository.save(player1);
			playerRepository.save(player2);
			playerRepository.save(player3);
			playerRepository.save(player4);
			playerRepository.save(player5);

			Date date = new Date();
			Date date2 = Date.from(date.toInstant().plusSeconds(1800));
			Date date3 = Date.from(date.toInstant().plusSeconds(3600));
			Date date4 = Date.from(date.toInstant().plusSeconds(5400));
			Date date5 = Date.from(date.toInstant().plusSeconds(7200));
			Date date6 = Date.from(date.toInstant().plusSeconds(9000));

			Game game1 = new Game(date);
			Game game2 = new Game(date2);
			Game game3 = new Game(date3);
			Game game4 = new Game(date4);

			gameRepository.save(game1);
			gameRepository.save(game2);
			gameRepository.save(game3);
			gameRepository.save(game4);


			GamePlayer gp1 = new GamePlayer(date);
			GamePlayer gp2 = new GamePlayer(date2);
			GamePlayer gp3 = new GamePlayer(date3);
			GamePlayer gp4 = new GamePlayer(date4);
			GamePlayer gp5 = new GamePlayer(date5);
			GamePlayer gp6 = new GamePlayer(date6);

			game1.addGamePlayer(gp1);
			game1.addGamePlayer(gp2);
			game2.addGamePlayer(gp3);
			game2.addGamePlayer(gp4);
			game2.addGamePlayer(gp5);
			game2.addGamePlayer(gp6);

			player1.addGamePlayer(gp1);
			player2.addGamePlayer(gp2);
			player1.addGamePlayer(gp3);
			player2.addGamePlayer(gp4);
			player4.addGamePlayer(gp5);
			player5.addGamePlayer(gp6);


			gamePlayerRepository.save(gp1);
			gamePlayerRepository.save(gp2);
			gamePlayerRepository.save(gp3);
			gamePlayerRepository.save(gp4);

			List <String> gridLocation1 = Arrays.asList("E3", "E4", "E5", "E6", "E7");
			List <String> gridLocation2 = Arrays.asList("A3", "A4", "A5", "A6");
			List <String> gridLocation3 = Arrays.asList("B3", "B4", "B5");
			List <String> gridLocation4 = Arrays.asList("C3", "C4", "C5");
			List <String> gridLocation5 = Arrays.asList("D3", "D4", "E5");

			Ship shipC = new Ship("Carrier", gridLocation1);
			Ship shipB = new Ship("Battleship", gridLocation2);
			Ship shipS = new Ship("Submarine", gridLocation3);
			Ship shipD = new Ship("Destroyer", gridLocation4);
			Ship shipP = new Ship("Patrol Boat", gridLocation5);

			gp1.addShip(shipC);
			gp1.addShip(shipB);
			gp1.addShip(shipS);
			gp1.addShip(shipD);
			gp1.addShip(shipP);

			gp2.addShip(shipC);
			gp2.addShip(shipB);
			gp2.addShip(shipS);
			gp2.addShip(shipD);
			gp2.addShip(shipP);

			shipRepository.save(shipC);
			shipRepository.save(shipB);
			shipRepository.save(shipS);
			shipRepository.save(shipD);
			shipRepository.save(shipP);

		};
	}
}
