package com.sh.arcinventorysaveticket.message;

import com.sh.arcinventorysaveticket.ArcInventorySaveTicket;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

@AllArgsConstructor
@Data
public class ArcMessage {

    private String prefix;
    private String message;

    public void send(CommandSender sender) {
        if (message.isEmpty()) return;
        sender.sendMessage(prefix + message);
    }

    public void send(ConsoleCommandSender console) {
        if (message.isEmpty()) return;
        console.sendMessage(prefix + message);
    }

    public void broadcast() {
        if (message.isEmpty()) return;
        ArcInventorySaveTicket.getInstance().getServer().broadcastMessage(prefix + message);
    }

    public String getText() {
        return prefix + message;
    }
}

