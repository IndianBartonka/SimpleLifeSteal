package me.indian.pl.Listeners;

import me.indian.pl.Main;
import me.indian.pl.Recipes.HeartRecipe;
import me.indian.pl.Utils.ConfigUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class HeartUse implements Listener {

    private final Main plugin;
    public HeartUse(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void HeartUse(PlayerInteractEvent e){
     Player p = (Player) e.getPlayer();
        Action a = e.getAction();

        ConfigUtil players = new ConfigUtil(plugin, "players.yml");
        Configuration conf = players.getConfig();
        int pl1 = players.getConfig().getInt(p.getDisplayName() + ".health");
        int plus = 1;
        if(p.getInventory().getItemInMainHand().getType() == Material.STRUCTURE_BLOCK && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§cHeart") ) {
            if (a == Action.RIGHT_CLICK_BLOCK || a == Action.RIGHT_CLICK_AIR) {
                players.getConfig().set(p.getDisplayName() + ".health", pl1 += plus);
                p.sendMessage(plugin.getConfig().getString("heart-use"));
                p.playSound(p.getLocation() , Sound.ITEM_DYE_USE,  20 ,10);
                p.getInventory().removeItem(HeartRecipe.item);
                p.setMaxHealth(pl1);
                players.save();
            }
        }
    }
}
