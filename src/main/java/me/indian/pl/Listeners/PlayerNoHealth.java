package me.indian.pl.Listeners;

import me.indian.pl.Main;
import me.indian.pl.Utils.ConfigUtil;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.checkerframework.checker.calledmethods.qual.EnsuresCalledMethods;

public class PlayerNoHealth implements Listener {

    private final Main plugin;

    public PlayerNoHealth(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void PlayerMO(PlayerMoveEvent e) {
        Player p = (Player) e.getPlayer();
        ConfigUtil players = new ConfigUtil(plugin, "players.yml");
        Configuration conf = plugin.getConfig();
        int pl1 = players.getConfig().getInt(p.getDisplayName() + ".health");
        p.setMaxHealth(pl1);
    }


    @EventHandler
    public void PlayerM(PlayerMoveEvent e) {
        Player p = (Player) e.getPlayer();
        ConfigUtil players = new ConfigUtil(plugin, "players.yml");
        Configuration conf = plugin.getConfig();
        int pl1 = players.getConfig().getInt(p.getDisplayName() + ".health");
        if (pl1 == 1) {
            p.sendMessage(conf.getString("No-health"));
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void Damage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {

            Player p = (Player) e.getEntity();
            ;
            ConfigUtil players = new ConfigUtil(plugin, "players.yml");
            Configuration conf = plugin.getConfig();
            int pl1 = players.getConfig().getInt(p.getDisplayName() + ".health");

            if (pl1 == 1) {
                e.setCancelled(true);
                p.sendMessage(conf.getString("No-health"));
            }
            if (e.getDamager() instanceof Player) {
                Player dmg = (Player) e.getDamager();
                int pl2 = players.getConfig().getInt(dmg.getDisplayName() + ".health");
                if (pl2 == 1) {
                    e.setCancelled(true);


                    dmg.sendMessage(conf.getString("No-health"));
                }
            }
        }
    }

    @EventHandler
    public void command(PlayerCommandPreprocessEvent e){
        Player p = (Player) e.getPlayer();
        ConfigUtil players = new ConfigUtil(plugin, "players.yml");
        Configuration conf = plugin.getConfig();
        int pl1 = players.getConfig().getInt(p.getDisplayName() + ".health");
        if (pl1 == 1) {
            e.setCancelled(true);
            p.sendMessage(conf.getString("No-health"));
        }
    }


    @EventHandler
    public void Break(BlockBreakEvent e){
        Player p = (Player) e.getPlayer();
        ConfigUtil players = new ConfigUtil(plugin, "players.yml");
        Configuration conf = plugin.getConfig();
        int pl1 = players.getConfig().getInt(p.getDisplayName() + ".health");
        if (pl1 == 1) {
            e.setCancelled(true);
            p.sendMessage(conf.getString("No-health"));
        }
    }
    @EventHandler
    public void Place(BlockPlaceEvent e){
        Player p = (Player) e.getPlayer();
        ConfigUtil players = new ConfigUtil(plugin, "players.yml");
        Configuration conf = plugin.getConfig();
        int pl1 = players.getConfig().getInt(p.getDisplayName() + ".health");
        if (pl1 == 1) {
            e.setCancelled(true);
            p.sendMessage(conf.getString("No-health"));
        }
    }
}
