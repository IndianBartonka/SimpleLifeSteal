package me.indian.pl.Listeners;

import me.indian.pl.Main;
import me.indian.pl.Utils.ConfigUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

public class JoinEvent implements Listener {

    private final Main plugin;

    public JoinEvent(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void JoinEvent(PlayerJoinEvent e) {
        Player p = (Player) e.getPlayer();
        ConfigUtil players = new ConfigUtil(plugin, "players.yml");
        int pkt = plugin.getConfig().getInt("start-health") * 2;
        if(!p.hasPlayedBefore()){
           int plp = players.getConfig().getInt(p.getDisplayName() + ".health");

           plp = pkt;
            players.getConfig().set(p.getDisplayName() + ".health" , pkt);

        }

        players.save();
    }

    @EventHandler
    public void JoinEventE(PlayerJoinEvent e) {
        Player p = (Player) e.getPlayer();
        ConfigUtil players = new ConfigUtil(plugin, "players.yml");
        int plus = 2;
        int pl1 = players.getConfig().getInt(p.getDisplayName() + ".health");

        if(pl1 == 0){
            players.getConfig().set(p.getDisplayName() + ".health", pl1 += plus);
        }

        players.save();
    }
}
