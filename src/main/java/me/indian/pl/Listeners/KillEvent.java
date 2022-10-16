package me.indian.pl.Listeners;

import me.indian.pl.Main;
import me.indian.pl.Recipes.HeartRecipe;
import me.indian.pl.Utils.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.ArrayList;
import java.util.List;

public class KillEvent implements Listener {

    private final Main plugin;

    public KillEvent(Main plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void KillEvent(PlayerDeathEvent e) {

        if (e.getEntity() instanceof Player && e.getEntity().getKiller() instanceof Player) {
            Player p = (Player) e.getEntity();
            Player k = (Player) e.getEntity().getKiller();

            ConfigUtil players = new ConfigUtil(plugin, "players.yml");

            int minus = 1;
            int plus = 1;


            int pl1 = players.getConfig().getInt(p.getDisplayName() + ".health");
            int pl2 = players.getConfig().getInt(k.getDisplayName() + ".health");

                if(plugin.getConfig().getBoolean("heartdrop")){
                    players.getConfig().set(p.getDisplayName() + ".health", pl1 -= minus);
                    p.getInventory().addItem(HeartRecipe.item);
                } else {
                    players.getConfig().set(p.getDisplayName() + ".health", pl1 -= minus);
                    players.getConfig().set(k.getDisplayName() + ".health", pl2 += plus);
                }

            p.setMaxHealth(pl1);
            k.setMaxHealth(pl2);
            players.save();


        }
    }
}
