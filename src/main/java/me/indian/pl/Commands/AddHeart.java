package me.indian.pl.Commands;

import me.indian.pl.Main;
import me.indian.pl.Utils.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AddHeart implements CommandExecutor {
    private final Main plugin;

    public AddHeart(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Configuration conf = plugin.getConfig();
            Player p = (Player) sender;
            int plus = 1;
            ConfigUtil players = new ConfigUtil(plugin, "players.yml");
            if(p.hasPermission("slf.add")){
                if(args.length == 0){
                    int pl1 = players.getConfig().getInt(p.getDisplayName() + ".health");
                    players.getConfig().set(p.getDisplayName() + ".health", pl1 += plus);
                    p.sendMessage(plugin.getConfig().getString("heart-use"));
                } else if (args.length == 1) {
                    Player cel = Bukkit.getPlayer(args[0]);

                    if(cel == null){
                        p.sendMessage(conf.getString("player-null"));
                    }
                    int pl1 = players.getConfig().getInt(cel.getDisplayName() + ".health");
                    players.getConfig().set(cel.getDisplayName() + ".health", pl1 += plus);
                    cel.sendMessage(plugin.getConfig().getString("heart-use"));


                }
            } else {
                p.sendMessage(conf.getString("non-perms"));
            }
            players.save();
        } else {
            sender.sendMessage("nope");
        }
        return false;
    }
}
