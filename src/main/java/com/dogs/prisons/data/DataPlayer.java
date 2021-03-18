package com.dogs.prisons.data;

import com.dogs.prisons.Prisons;
import com.dogs.prisons.filemanager.LevelCost;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DataPlayer {

    public Player player;
    private int level, xp;
    private double balance;

    public DataPlayer(Player player){
        this.player = player;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        if (milestone()){
            Bukkit.broadcastMessage(player.getDisplayName() + " has leveled up to " + getLevel());
        }
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
        levelUp();
    }

    public void levelUp(){
        int levelCost = LevelCost.getLevelCost(getLevel());
        if (getXp() > levelCost){
            setLevel(getLevel()+1);
        }
    }

    public boolean milestone(){
        List<Integer> milestones = new ArrayList<>();
        milestones.add(5);
        milestones.add(10);
        milestones.add(15);
        milestones.add(20);
        milestones.add(25);
        milestones.add(30);
        milestones.add(35);
        milestones.add(40);
        milestones.add(45);
        milestones.add(50);
        milestones.add(55);
        milestones.add(60);
        milestones.add(65);
        milestones.add(70);
        milestones.add(75);
        milestones.add(80);
        milestones.add(85);
        milestones.add(90);
        milestones.add(95);
        milestones.add(100);
        return milestones.contains(getLevel());
    }

    public double getBalance() {
        this.balance = Prisons.getEconomy().getBalance(player);
        return balance;
    }
}
