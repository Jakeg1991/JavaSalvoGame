package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String shipType;

    public Ship() { }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gamePlayer_id")
    private GamePlayer gamePlayer;

    public Ship(String shipType){
        this.shipType=shipType;

    }
    public long getShipId() {
        return id;
    }

    public void seShipId(long id) {
        this.id = id;
    }

    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setShip(this);
        gamePlayer.add(gamePlayer);
    }
    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }




}