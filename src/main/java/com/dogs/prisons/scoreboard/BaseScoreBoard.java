package com.dogs.prisons.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class BaseScoreBoard {
    private Scoreboard sb;
    private String name;
    private DisplaySlot ds;
    private Track[] tracks;

    public BaseScoreBoard(String name, DisplaySlot ds, Track... tracks) {
        this(Bukkit.getScoreboardManager().getNewScoreboard(), name, ds, tracks);
    }

    public BaseScoreBoard(Scoreboard sb, String name, DisplaySlot ds, Track... tracks) {
        this.name = name;
        this.ds = ds;
        this.tracks = tracks;
        this.sb = sb;

        Objective obj = this.sb.registerNewObjective(ChatColor.stripColor(name), "dummy");
        obj.setDisplaySlot(ds);
        obj.setDisplayName(name);

        for(Track t : tracks) {
            if(t.getTeam() != null) {
                Team team = this.sb.registerNewTeam(t.getTeam());
                team.addEntry(t.getBase());
                team.setPrefix(t.getPrefix());
                team.setSuffix(t.getSuffix());
            }
            obj.getScore(t.getBase()).setScore(t.getLine());
        }
    }

    public Scoreboard getScoreboard() {
        return sb;
    }

    public void updateScoreboard() {
        for(Track t : tracks) {
            if(t.getTeam() != null) {
                Team team = sb.getTeam(t.getTeam());
                team.setPrefix(t.getPrefix());
                team.setSuffix(t.getSuffix());
            }
        }
    }

    /**
     * @return String that may include ChatColor.
     */
    public String getName() {
        return name;
    }

    public DisplaySlot getDisplaySlot() {
        return ds;
    }

    public Track[] getTracks() {
        return tracks;
    }

    public Track getTrackByBase(String base) {
        for(Track t : tracks) if(t.getBase().equals(base)) return t;
        return null;
    }

    public Track getTrackByLine(int line) {
        for(Track t : tracks) if(t.getLine() == line) return t;
        return null;
    }

    public static class Track {

        private String team, base, prefix, suffix;
        private int line;

        public Track(String base, int line) {
            this(null, base, line, "", "");
        }

        public Track(String team, String base, int line) {
            this(team, base, line, "", "");
        }

        public Track(String team, String base, int line, String prefix, String suffix) {
            this.team = team;
            this.base = base;
            this.line = line;
            this.prefix = prefix;
            this.suffix = suffix;
        }

        public String getTeam() {
            return team;
        }

        public String getBase() {
            return base;
        }

        public int getLine() {
            return line;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getSuffix() {
            return suffix;
        }

        public void setSuffix(String suffix) {
            this.suffix = suffix;
        }
    }
}
