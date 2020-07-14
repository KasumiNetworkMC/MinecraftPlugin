package net.craftingstore.bukkit.inventory;

import net.craftingstore.bukkit.Configuration;
import net.craftingstore.bukkit.CraftingStoreBukkit;
import net.craftingstore.bukkit.util.ChatColorUtil;
import net.craftingstore.bukkit.util.ItemBuilder;
import net.craftingstore.bukkit.util.XMaterial;
import net.craftingstore.core.CraftingStore;
import net.craftingstore.core.models.api.inventory.CraftingStoreInventory;
import net.craftingstore.core.models.api.inventory.InventoryItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryBuilder {

    private CraftingStoreBukkit instance;

    public InventoryBuilder(CraftingStoreBukkit instance) {
        this.instance = instance;
    }

    public Inventory buildInventory(CraftingStoreInventory csInventory) {
        for (int i = 0; i < 2; i++) {
            InventoryItem inventoryItem = csInventory.getByIndex(i);

            if (i == 0) {
                inventoryItem.setName("Ranks");
            } else if (i == 1) {
                inventoryItem.setName("Casino");
            }

            inventoryItem.setDescription(null);
        }

        return buildInventory(csInventory, null);
    }

    public Inventory buildInventory(CraftingStoreInventory csInventory, CraftingStoreInventoryHolder parent) {
        String title = ChatColorUtil.color(Configuration.INVENTORY_NAME);
        Inventory inventory = Bukkit.createInventory(new CraftingStoreInventoryHolder(csInventory, parent), csInventory.getSize(), title);

        int i = 0;
        for (InventoryItem inventoryItem : csInventory.getContent()) {
            String name = inventoryItem.getName();

            Material material;

            if (Configuration.RANK_ITEMS.containsKey(name)) {
                material = Configuration.RANK_ITEMS.get(name).getMaterial();
            } else {
                material = Material.CHEST;
            }

            List<String> lore = null;

            if (inventoryItem.getDescription() != null && inventoryItem.getDescription().length != 0) {
                lore = Arrays.stream(inventoryItem.getDescription()).map(d ->
                        ChatColor.translateAlternateColorCodes('&', d)).collect(Collectors.toList());
            }
            ItemBuilder itemBuilder;

             if (lore != null) {
                 itemBuilder = new ItemBuilder(material, ChatColor.GOLD + name, lore);
             } else {
                 itemBuilder = new ItemBuilder(material, ChatColor.GOLD + name);
             }

            inventory.setItem(inventoryItem.getIndex(), itemBuilder.build());
        }

        return inventory;
    }
}
