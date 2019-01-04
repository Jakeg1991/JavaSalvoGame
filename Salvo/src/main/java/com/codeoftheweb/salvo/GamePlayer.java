package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private Date joinDate;

    public GamePlayer(Date joinDate) {
        this.joinDate = joinDate;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    @OneToMany(mappedBy="gamePlayer",fetch = FetchType.EAGER)
    Set<Ship> ships= new HashSet<>();

    public GamePlayer() {
    }

    public long getGamePlayerId() {
        return id;
    }

    public void setGamePlayerId(long id) {
        this.id = id;
    }

    public Date getjoinDate() {
        return joinDate;
    }

    public void setCreationDate(Date date) {
        this.joinDate = date;
    }

    public Player getPlayer() {
        return player;
    }

    public void addShip(Ship ship) {
        ship.setGamePlayer(this);
        ships.add(ship);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }}

