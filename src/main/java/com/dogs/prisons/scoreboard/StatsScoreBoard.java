package com.dogs.prisons.scoreboard;

import com.dogs.prisons.charm.Pickaxe;
import com.dogs.prisons.data.DataPlayer;
import com.dogs.prisons.enchant.ItemSet;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

public class StatsScoreBoard {

    DataPlayer data;
    Scoreboard scoreboard;
    BaseScoreBoard.Track level, money, charm;
    BaseScoreBoard baseScoreBoard;

    public StatsScoreBoard(DataPlayer data) {
        this.data = data;
        this.scoreboard = data.player.getScoreboard();
        this.baseScoreBoard = data.statsScoreBoard;
        if (baseScoreBoard == null)
            createScoreBoard();
    }

    public void updateFullScoreBoard(){
        updateLevelAndXp();
        updateCash();
        updateCharm();
    }

    public void updateLevelAndXp(){
        level.setPrefix("Xp:" + data.getXp());
        level.setSuffix(" level:" + data.getLevel());
        this.baseScoreBoard.updateScoreboard();
    }

    public void updateCash(){
        money.setPrefix("");
        money.setSuffix("" + data.getBalance());
        this.baseScoreBoard.updateScoreboard();
    }
    public void updateCharm() {
        Pickaxe pickaxe;
        Bukkit.broadcastMessage(data.player.getItemInHand().toString());
        if (data.player.getItemInHand() == null)
            if (charm != null)
                scoreboard.resetScores(charm.getTeam());
        else {
            pickaxe = new Pickaxe(data.player.getItemInHand());
            if (charm != null) {
                charm.setPrefix("Charm: " + pickaxe.getCharm());
                charm.setSuffix("" + pickaxe.getCharmMax());
            } else {
                this.charm = new BaseScoreBoard.Track("charm", ChatColor.GRAY + "/", 0,
                        "Charm: " + pickaxe.getCharm(), "" + pickaxe.getCharmMax());
            }
        }
        this.baseScoreBoard.updateScoreboard();
    }

    private void createScoreBoard() {
        Pickaxe pickaxe = null;
        ItemStack hand = data.player.getItemInHand();
        this.level = new BaseScoreBoard.Track("level", ChatColor.GRAY + "/", 0,
                "Xp:" + data.getXp(), " level:" + data.getLevel());

        this.money = new BaseScoreBoard.Track("cash", ChatColor.GREEN + "$", 1,
                "", "" + data.getBalance());
        if (data.player.getItemInHand().getType() != Material.AIR
                && ItemSet.PICKAXE.getItems().contains(hand.getType())) {
            pickaxe = new Pickaxe(hand);
            this.charm = new BaseScoreBoard.Track("charm", ChatColor.GRAY + " / ", 5,
                    "Charm: " + pickaxe.getCharm(), "" + pickaxe.getCharmMax());
            this.baseScoreBoard = new BaseScoreBoard(ChatColor.GOLD + " " + ChatColor.BOLD + "Player level", DisplaySlot.SIDEBAR,
                    level, money, charm);
        } else
        this.baseScoreBoard = new BaseScoreBoard(ChatColor.GOLD + " " + ChatColor.BOLD + "Player level", DisplaySlot.SIDEBAR,
                level, money);
        data.player.setScoreboard(this.baseScoreBoard.getScoreboard());
        this.scoreboard = this.baseScoreBoard.getScoreboard();
    }
}
