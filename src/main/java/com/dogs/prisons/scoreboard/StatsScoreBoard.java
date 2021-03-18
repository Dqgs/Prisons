package com.dogs.prisons.scoreboard;

import com.dogs.prisons.data.DataPlayer;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

public class StatsScoreBoard {

    DataPlayer data;
    Scoreboard scoreboard;
    BaseScoreBoard.Track level, money;
    BaseScoreBoard baseScoreBoard;

    public StatsScoreBoard(DataPlayer data){
        this.data = data;
        this.scoreboard = data.player.getScoreboard();
        if (scoreboard == null) {
            createScoreBoard();
        } else {
            this.baseScoreBoard = new BaseScoreBoard(this.scoreboard, ChatColor.GOLD + " " + ChatColor.BOLD + "Player level", DisplaySlot.SIDEBAR,
                    money, level);
            data.player.setScoreboard(baseScoreBoard.getScoreboard());
        }
    }

    public void updateFullScoreBoard(){
        updateLevelAndXp();
        updateCash();
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

    private void createScoreBoard(){
        this.level = new BaseScoreBoard.Track("level", ChatColor.GRAY + "/", 0,
                "Xp:" + data.getXp(), " level:" + data.getLevel());

        this.money = new BaseScoreBoard.Track("cash", ChatColor.GREEN + "$", 1,
                "", "" + data.getBalance());

        this.baseScoreBoard = new BaseScoreBoard(ChatColor.GOLD + " " + ChatColor.BOLD + "Player level", DisplaySlot.SIDEBAR,
                money, level);

        data.player.setScoreboard(this.baseScoreBoard.getScoreboard());
        this.scoreboard = this.baseScoreBoard.getScoreboard();
    }
}
