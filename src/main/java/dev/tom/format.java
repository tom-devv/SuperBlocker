package dev.tom;

import org.bukkit.ChatColor;

public class format {
    public static String format(String s){
        return ChatColor.translateAlternateColorCodes((char)'&', (String)s);
    }
}
