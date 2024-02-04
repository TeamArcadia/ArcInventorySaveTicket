package com.sh.arcinventorysaveticket.util;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtil {

    public static String colorize (String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> loreColorzie (List<String> lore) {

        List<String> translatedLore = new ArrayList<>();
        for (String loreLine : lore) {
            translatedLore.add(ChatColor.translateAlternateColorCodes('&', loreLine));
        }

        return translatedLore;
    }


    public static String hex (String msg) {
        if (Integer.parseInt(Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].split("_")[1]) < 16)
            return msg;
        Matcher matcher = Pattern.compile("&(#\\w{6})").matcher(ChatColor.translateAlternateColorCodes('&', msg));
        StringBuffer buffer = new StringBuffer();
        while (matcher.find())
            matcher.appendReplacement(buffer, ChatColor.of(matcher.group(1)).toString());
        return matcher.appendTail(buffer).toString();
    }
}
