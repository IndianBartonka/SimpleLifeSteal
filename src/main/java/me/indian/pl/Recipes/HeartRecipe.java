package me.indian.pl.Recipes;

import me.indian.pl.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class HeartRecipe {


    private final Main plugin;

    public HeartRecipe(Main plugin) {
        this.plugin = plugin;
    }

    public static ItemStack item = new ItemStack(Material.STRUCTURE_BLOCK , 1);
    public static void HeartRecipe (Main plugin) {

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§cHeart");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("");
        meta.setLore(lore);
        item.setItemMeta(meta);

        ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(plugin, "Heart"), item);

        rc.shape("DGD", "GKG", "DGD");

        rc.setIngredient('D', Material.DIAMOND);
        rc.setIngredient('K', Material.ENCHANTED_GOLDEN_APPLE);
        rc.setIngredient('G' , Material.GOLDEN_APPLE);

        plugin.getServer().addRecipe(rc);

    }
}
