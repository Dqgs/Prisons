package com.dogs.prisons.events;

import com.dogs.prisons.Prisons;
import com.dogs.prisons.data.DataPlayer;
import com.dogs.prisons.filemanager.PlayerData;
import com.dogs.prisons.scoreboard.StatsScoreBoard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event){
        Prisons.getInstance().getDataManager().add(event.getPlayer());
        PlayerData.loadPlayer(event.getPlayer());
        DataPlayer data = Prisons.getInstance().getDataManager().getDataPlayer(event.getPlayer());;
        StatsScoreBoard scoreBoard = new StatsScoreBoard(data);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLeave(PlayerQuitEvent event){
        PlayerData.savePlayer(event.getPlayer());
        Prisons.getInstance().getDataManager().remove(event.getPlayer());
    }

}
