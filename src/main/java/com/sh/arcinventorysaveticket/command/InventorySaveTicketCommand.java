package com.sh.arcinventorysaveticket.command;

import com.sh.arcinventorysaveticket.command.sub.GetTicketCommand;
import com.sh.arcinventorysaveticket.command.sub.ReloadConfigCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class InventorySaveTicketCommand implements TabExecutor {

    private final Map<String, SubCommand> subCommands = new HashMap<>();

    public InventorySaveTicketCommand(JavaPlugin plugin) {
        PluginCommand command = plugin.getCommand("인벤세이브권");
        if (command != null) {
            command.setExecutor(this);
            command.setTabCompleter(this);
            registerSubCommands();
        }
    }

    private void registerSubCommands() {
        registerSubCommand(new GetTicketCommand());
        registerSubCommand(new ReloadConfigCommand());
    }

    private void registerSubCommand(SubCommand subCommand) {
        subCommands.put(subCommand.getKoName(), subCommand);
    }

    private boolean hasPermission(CommandSender sender, SubCommand subCommand) {
        String permission = "arc.inventorysaveticket." + subCommand.getPermission(sender);
        if (permission == null || permission.isEmpty()) {
            return true;
        }
        return sender.hasPermission(permission);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {

            Set<SubCommand> uniqueSubCommands = new HashSet<>(subCommands.values());

            sender.sendMessage("§8§m                                                     §r");
            sender.sendMessage("§7[ §6§l인벤세이브권§7§l관리 §f| §f명령어 도움말 §7]");

            for (SubCommand subCommand : uniqueSubCommands) {
                sender.sendMessage("§b▶ §f/인벤세이브권 " + subCommand.getKoName() + " " + subCommand.getKoUsage() );
                sender.sendMessage("      §7└" + subCommand.getKoDescription());
            }

            sender.sendMessage("§8§m                                                     §r");
            return true;
        }

        SubCommand subCommand = subCommands.get(args[0].toLowerCase());

        if (subCommand != null && hasPermission(sender, subCommand)) {
            subCommand.execute(sender, args);
            return true;
        }
        return false;
    }

    @Override
    @Nullable
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            for (SubCommand subCommand : subCommands.values()) {
                completions.add(subCommand.getKoName());
            }
        } else {
            SubCommand subCmdInstance = subCommands.get(args[0].toLowerCase());
            if (subCmdInstance != null) {
                completions = subCmdInstance.tabComplete(sender, args);
            }
        }
        return StringUtil.copyPartialMatches(args[args.length - 1], completions, new ArrayList<>());
    }
}

