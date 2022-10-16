package me.indian.pl.Otchers;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.indian.pl.Main;
import me.indian.pl.Utils.ConfigUtil;
import org.bukkit.entity.Player;


import java.util.ArrayList;

public class PvpPointsPlaceholders extends PlaceholderExpansion {

    private final Main plugin;

    public PvpPointsPlaceholders(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getIdentifier() {
        return "simplelf";
    }

    @Override
    public String getAuthor() {
        return "IndianPL";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
        ConfigUtil players = new ConfigUtil(plugin, "players.yml");
        if (identifier.equals("maxhealth")) {
            int pl1 = players.getConfig().getInt(p.getDisplayName() + ".health");

            return pl1 + "";
        }
        return null;
    }
}
