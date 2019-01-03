package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long shipId;
    private String shipType;

    public Ship() { }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gamePlayer_id")
    private GamePlayer gamePlayer;

    public List<String> getGridLocations() {
        return gridLocations;
    }

    public void setGridLocations(List<String> gridLocations) {
        this.gridLocations = gridLocations;
    }

    @ElementCollection
    @Column(name="grid_locations")
    private List<String> gridLocations = new ArrayList<>();

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public Ship(String shipType, List<String> gridLocation){
        this.shipType = shipType;
        this.gridLocations = gridLocation;

    }
    public long getShipId() {
        return shipId;
    }

    public void setShipId(long shipId) {
        this.shipId = shipId;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }




}