package com.dogs.prisons.data;

import org.bukkit.entity.Player;

public class DataPlayer {
    public Player player;
    public int level, xp;
    public Rank rank;

    public DataPlayer(Player player){
        this.player = player;
    }
}
