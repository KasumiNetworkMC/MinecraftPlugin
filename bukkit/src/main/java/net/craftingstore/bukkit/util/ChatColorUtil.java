package net.craftingstore.bukkit.util;

import org.bukkit.ChatColor;

public class ChatColorUtil {

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
