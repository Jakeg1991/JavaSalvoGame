package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
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
                            put("id", gamePlayer.getGamePlayerId());
                            put("player", getPlayerData(gamePlayer.getPlayer()));
                        }}).collect(Collectors.toList()));  //this "collects" all the separate hashmaps into one list object.
            }}).collect(Collectors.toList());
    }
    
    @RequestMapping("/game_view/{gamePlayerId}")

    private Map<String, Object> getSingleGame(@PathVariable long gamePlayerId){

        GamePlayer gp = gamePlayerRepository.findById(gamePlayerId).orElse(null);

        return new LinkedHashMap<String, Object>(){{
            put("gameId", gp.getGame().getGameId());
            put("created", gp.getGame().getDate());
            put("gamePlayers", new ArrayList<LinkedHashMap<String, Object>>(){{
                add(new LinkedHashMap<String, Object>(){{
                    gp.getGame().getGamePlayers()
                            .stream()
                            .forEach(gamePlayer ->
                                    put("players", getGamePlayerData(gamePlayer.getGame())));

                }});
            }});
                put("ships", getShipData(gp.getOwnedShips()));
        }};
    }

    private HashMap<String, Object> getPlayerData(Player player){
            return new HashMap<String, Object>(){{
                put("id", player.getPlayerId());
                put("email", player.getEmail());
            }};
    }
    private List<LinkedHashMap<String,Object>> getGamePlayerData(Game game){
        return game.getGamePlayers()
                .stream()
                .map(gamePlayer -> new LinkedHashMap<String, Object>(){{
                    put("id", gamePlayer.getGamePlayerId());
                    put("player", getPlayerData(gamePlayer.getPlayer()));
                }}).collect(Collectors.toList());
    }
    private List<LinkedHashMap<String,Object>> getShipData(Set<Ship> getOwnedShips) {
        return getOwnedShips
                .stream()
                .map(ship -> new LinkedHashMap<String, Object>() {
                    {
                        put("type", ship.getShipType());
                        put("locations", ship.getGridLocations());
                    }
                }).collect(Collectors.toList());
    }



}

