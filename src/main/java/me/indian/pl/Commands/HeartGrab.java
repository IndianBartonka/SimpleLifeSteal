package me.indian.pl.Commands;

import me.indian.pl.Main;
import me.indian.pl.Recipes.HeartRecipe;
import me.indian.pl.Utils.ConfigUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HeartGrab implements CommandExecutor {

    private final Main plugin;

    public HeartGrab(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            Configuration conf = plugin.getConfig();
            int minus = 1;
            ConfigUtil players = new ConfigUtil(plugin, "players.yml");
            int pl1 = players.getConfig().getInt(p.getDisplayName() + ".health");
            players.getConfig().set(p.getDisplayName() + ".health", pl1 -= minus);
            p.sendMessage(conf.getString("heart-grab"));
            p.getInventory().addItem(HeartRecipe.item);
            p.setMaxHealth(pl1);
            players.save();
        } else {
            sender.sendMessage("Nope");
        }
        return false;
    }
}
