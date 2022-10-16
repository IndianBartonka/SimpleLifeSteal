package me.indian.pl.Commands;

import me.indian.pl.Main;
import me.indian.pl.Utils.HealthUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class LeaderBoeard implements CommandExecutor, Listener {

    private final Main plugin;

    public LeaderBoeard(Main plugin) {
        this.plugin = plugin;

    }

    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String label, String[] args) {
        if (sender instanceof Player) {
            Configuration conf = plugin.getConfig();
            Player p = (Player) sender;
            ArrayList<String> top = HealthUtil.getTop(plugin);


            if (args.length == 0) {
                for (String ar : top) {
                    p.sendMessage(ar);
                }


            } else if (args[0].equalsIgnoreCase("gui")) {
                Inventory inv = Bukkit.createInventory(null, 27, conf.getString("gui-name"));
                ItemStack miecz = new ItemStack(Material.RED_DYE  );
                ItemMeta meta = miecz.getItemMeta();
                meta.setDisplayName(conf.getString("item-name"));
                meta.setLore(HealthUtil.getTop(plugin));
                miecz.setItemMeta(meta);

                inv.setItem(13, miecz);
                p.openInventory(inv);
            }

        } else {
            sender.sendMessage("nope");
        }
        return false;
    }


    @EventHandler
    public void InventoryClick(InventoryClickEvent e){
        Configuration conf = plugin.getConfig();
        Player p = (Player) e.getWhoClicked();
        if(e.getView().getTitle().equals(conf.getString("gui-name"))  ){
            e.setCancelled(true);
        }

    }
}