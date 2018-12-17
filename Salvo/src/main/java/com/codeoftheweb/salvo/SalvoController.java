package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SalvoController {

    private GameRepository gameRepository;
    private GamePlayerRepository gamePlayerRepository;

    @Autowired
    public SalvoController(GameRepository gameRepository, GamePlayerRepository gamePlayerRepository) {
        this.gameRepository = gameRepository;
        this.gamePlayerRepository = gamePlayerRepository;
    }

    @RequestMapping(value = "/games")
    public List<Game> findGames(){
        return gameRepository.findAll();
    }
}
