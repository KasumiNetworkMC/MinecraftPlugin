package net.craftingstore.bukkit;

import net.craftingstore.bukkit.inventory.RankItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    public static final Map<String, RankItem> RANK_ITEMS = new HashMap<>();
    public static final String INVENTORY_NAME = ChatColor.GOLD + "Kasumi Store";

    public static void init() {
        RANK_ITEMS.put("blaze", new RankItem("Blaze", null, Material.BLAZE_ROD));
    }
}
