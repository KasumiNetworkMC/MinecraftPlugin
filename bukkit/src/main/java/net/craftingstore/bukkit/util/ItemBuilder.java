package net.craftingstore.bukkit.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

    private Material type;
    private String name;
    private List<String> lore;
    private int amount;
    private short data;
    private boolean unbreakable = false;

    public ItemBuilder(Material type, String name) {
        this.type = type;
        this.name = name;
        this.lore = null;
        this.amount = 1;
    }

    public ItemBuilder(Material type, String name, String... lore) {
        this.type = type;
        this.name = name;
        this.lore = Arrays.asList(lore);
        this.amount = 1;
    }

    public ItemBuilder(Material type, String name, List<String> lore) {
        this.type = type;
        this.name = name;
        this.lore = lore;
        this.amount = 1;
    }

    public ItemBuilder(Material type, String name, short data) {
        this(type, name);

        this.data = data;
    }

    public ItemBuilder(Material type, String name, boolean unbreakable, String... lore) {
        this(type, name, lore);

        this.unbreakable = unbreakable;
    }

    public ItemBuilder(Material type, String name, int amount, String... lore) {
        this.type = type;
        this.name = name;
        this.lore = Arrays.asList(lore);
        this.amount = amount;
    }

    public ItemBuilder(Material type, String name, int amount, short data) {
        this.type = type;
        this.name = name;
        this.lore = null;
        this.amount = amount;
        this.data = data;
    }

    public ItemBuilder(Material type, String name, int amount, short data, String... lore) {
        this.type = type;
        this.name = name;
        this.lore = Arrays.asList(lore);
        this.amount = amount;
        this.data = data;
    }

    public ItemBuilder(Material type, String name, int amount, short data, List<String> lore) {
        this.type = type;
        this.name = name;
        this.lore = lore;
        this.amount = amount;
        this.data = data;
    }

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(this.type, this.amount, this.data);
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (lore != null) {
            itemMeta.setLore(lore);
        }

        itemMeta.setDisplayName(this.name);
        itemMeta.spigot().setUnbreakable(this.unbreakable);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
