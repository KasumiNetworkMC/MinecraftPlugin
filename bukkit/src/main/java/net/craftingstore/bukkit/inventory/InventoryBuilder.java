package net.craftingstore.bukkit.inventory;

import net.craftingstore.bukkit.Configuration;
import net.craftingstore.bukkit.CraftingStoreBukkit;
import net.craftingstore.bukkit.util.ChatColorUtil;
import net.craftingstore.bukkit.util.ItemBuilder;
import net.craftingstore.bukkit.util.XMaterial;
import net.craftingstore.core.models.api.inventory.CraftingStoreInventory;
import net.craftingstore.core.models.api.inventory.InventoryItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryBuilder {

    private CraftingStoreBukkit instance;

    public InventoryBuilder(CraftingStoreBukkit instance) {
        this.instance = instance;
    }

    public Inventory buildInventory(CraftingStoreInventory csInventory) {
        return buildInventory(csInventory, null);
    }

    public Inventory buildInventory(CraftingStoreInventory csInventory, CraftingStoreInventoryHolder parent) {
        String title;

        if (csInventory.getTitle() == null) {
            title = ChatColorUtil.color(Configuration.FALL_BACK_INVENTORY_TITLE);
        } else {
            title = ChatColorUtil.color(csInventory.getTitle());
        }

        Inventory inventory = Bukkit.createInventory(new CraftingStoreInventoryHolder(csInventory, parent), csInventory.getSize(), title);

        for (InventoryItem inventoryItem : csInventory.getContent()) {
            String name = ChatColorUtil.color(inventoryItem.getName());

            Material material;

            if (inventoryItem.getIcon() == null) {
                material = Material.CHEST;
            } else {
                XMaterial xMaterial = XMaterial.fromString(inventoryItem.getIcon().getMaterial());

                if (xMaterial == null || xMaterial.parseMaterial() == null) {
                    material = Material.CHEST;
                } else {
                    material = xMaterial.parseMaterial();
                }
            }

            List<String> lore = null;

            if (inventoryItem.getDescription() != null && inventoryItem.getDescription().length != 0) {
                lore = Arrays.stream(inventoryItem.getDescription()).map(ChatColorUtil::color).collect(Collectors.toList());
            }
            ItemBuilder itemBuilder;

             if (lore != null) {
                 itemBuilder = new ItemBuilder(material, name, inventoryItem.getIcon().getAmount(), lore);
             } else {
                 itemBuilder = new ItemBuilder(material, name, inventoryItem.getIcon().getAmount());
             }

            inventory.setItem(inventoryItem.getIndex(), itemBuilder.build());
        }

        return inventory;
    }
}
