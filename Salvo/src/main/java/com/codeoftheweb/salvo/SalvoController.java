package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<HashMap<String,Object>> findGames() {
        return gameRepository.findAll().stream().map(game -> new LinkedHashMap<String, Object>() {{
                put("id", game.getGameId());
                put("created", game.getDate());
                put("gamePlayers", game.getGamePlayers()
                        .stream()
                        .map(gamePlayer -> new LinkedHashMap<String, Object>() {{
                            put("id", gamePlayer.getId());
                            put("player", getPlayerData(gamePlayer.getPlayer()));
                        }}).collect(Collectors.toList()));  //this "collects" all the separate hashmaps into one list object.
            }}).collect(Collectors.toList());
    }

    private HashMap<String, Object> getPlayerData(Player player){
            return new HashMap<String, Object>(){{
                put("id", player.getPlayerId());
                put("email", player.getEmail());
            }};
    }
}
