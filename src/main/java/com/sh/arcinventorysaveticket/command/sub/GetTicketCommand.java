package com.sh.arcinventorysaveticket.command.sub;

import com.sh.arcinventorysaveticket.ArcInventorySaveTicket;
import com.sh.arcinventorysaveticket.command.SubCommand;
import com.sh.arcinventorysaveticket.message.MessageContext;
import com.sh.arcinventorysaveticket.message.MessageType;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;

public class GetTicketCommand implements SubCommand {
    @Override
    public String getKoName() {
        return "발급";
    }

    @Override
    public String getKoDescription() {
        return "설정한 인벤세이브권을 발급합니다.";
    }

    @Override
    public String getKoUsage() {
        return "";
    }

    @Override
    public String getPermission(CommandSender sender) {
        return "get";
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        FileConfiguration config = ArcInventorySaveTicket.getInstance().getConfig();
        ConfigurationSection ticketItemSec = config.getConfigurationSection("item-manage");

        MessageContext messageContext = MessageContext.getInstance();
        Player player = (Player) sender;

        if (ticketItemSec == null) {
            messageContext.get(MessageType.MAIN, "no_save_item").send(sender);
        } else {
            ItemStack savedItemSec = ticketItemSec.getItemStack("ticket-item");
            player.getInventory().addItem(savedItemSec.clone());
        }
        messageContext.get(MessageType.MAIN, "get_ticket").send(sender);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }
}
