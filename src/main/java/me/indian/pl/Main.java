package me.indian.pl;

import me.indian.pl.Commands.AddHeart;
import me.indian.pl.Commands.HeartGrab;
import me.indian.pl.Commands.LeaderBoeard;
import me.indian.pl.Commands.Reload;
import me.indian.pl.Listeners.HeartUse;
import me.indian.pl.Listeners.JoinEvent;
import me.indian.pl.Listeners.KillEvent;
import me.indian.pl.Listeners.PlayerNoHealth;
import me.indian.pl.Otchers.Metrics;
import me.indian.pl.Otchers.PvpPointsPlaceholders;
import me.indian.pl.Otchers.UpdateChecker;
import me.indian.pl.Recipes.HeartRecipe;
import me.indian.pl.Utils.ConfigUtil;
import me.indian.pl.Utils.MessageApi;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {


    public int id = 105783;
    public String prefix = "§b[§6Simple§aLife§cSteal§b] ";
    public String link = "https://www.spigotmc.org/resources/simplelifesteal.105783/";
    @Override
    public void onEnable() {

        int pluginId = 16671;
        Metrics metrics = new Metrics(this, pluginId);


        metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));


        new UpdateChecker(this, id).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version)) {
                getLogger().info(prefix + "There is not a new update available.");

            } else {
                getLogger().info (prefix + " There is a new update available.");
                getLogger().info(prefix + " Your version " + this.getDescription().getVersion() + " new version " + version);

            }
        });

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {

            new PvpPointsPlaceholders(this).register();
        } else {
            Bukkit.getConsoleSender().sendMessage("You don't have PlaceholderAPI, this plugin is not required");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        saveDefaultConfig();
        PluginManager pm = getServer().getPluginManager();
        String name = getDescription().getName();
        String version = getDescription().getVersion();
        ConfigUtil players = new ConfigUtil(this ,"players.yml" );
        players.save();

        Bukkit.getConsoleSender().sendMessage("-----------------------------");
        Bukkit.getConsoleSender().sendMessage("\t " + name + " " + version);
        Bukkit.getConsoleSender().sendMessage("-----------------------------");

        pm.registerEvents(new JoinEvent(this) , this);
        pm.registerEvents(new KillEvent(this) , this);
        pm.registerEvents(new LeaderBoeard(this) , this);
        pm.registerEvents(new PlayerNoHealth(this) , this);
        pm.registerEvents(new HeartUse(this) , this);
        pm.registerEvents(this , this);
        getCommand("ls").setExecutor(new LeaderBoeard(this));
        getCommand("slfr").setExecutor(new Reload(this));
        getCommand("heart").setExecutor(new HeartGrab(this));
        getCommand("addheart").setExecutor(new AddHeart(this));
        HeartRecipe.HeartRecipe(this );


    }

    @EventHandler
    public void OpJoin(PlayerJoinEvent e){
        Player p = (Player) e.getPlayer();


        if (p.isOp()) {
            new UpdateChecker(this, 105783).getVersion(version -> {
                if (!(this.getDescription().getVersion().equals(version))) {
                    MessageApi.Chat(p, prefix + "§bThere is a new update available.");
                    MessageApi.HoverMessageCopy(p,
                            prefix + "§bYour version " + this.getDescription().getVersion() + " new version " + version,
                            link,
                            "Copy download link");

//                    p.sendMessage( prefix + "§bYour version " + this.getDescription().getVersion() + " new version " + version);
                }
            });
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
