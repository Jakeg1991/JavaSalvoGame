package com.codeoftheweb.salvo;

        import org.hibernate.annotations.GenericGenerator;

        import javax.persistence.*;
        import java.util.Date;
        import java.util.HashSet;
        import java.util.Set;


@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private Date date;

    public Game() { }

    @OneToMany(mappedBy="game",fetch = FetchType.EAGER)
    Set<GamePlayer> gamePlayers= new HashSet<>();

    public Game(Date date){
        this.date=date;

    }
    public long getGameId() {
        return id;
    }

    public void setGameId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setGame(this);
        gamePlayers.add(gamePlayer);
    }




}