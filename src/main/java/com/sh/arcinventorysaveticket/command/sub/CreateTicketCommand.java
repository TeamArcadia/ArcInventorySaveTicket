package com.sh.arcinventorysaveticket.command.sub;

import com.sh.arcinventorysaveticket.ArcInventorySaveTicket;
import com.sh.arcinventorysaveticket.command.SubCommand;
import com.sh.arcinventorysaveticket.message.MessageContext;
import com.sh.arcinventorysaveticket.message.MessageType;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class CreateTicketCommand implements SubCommand {

    public static HashMap<UUID, ItemStack> savedItems = new HashMap<>();
    @Override
    public String getKoName() {
        return "생성";
    }

    @Override
    public String getKoDescription() {
        return "손에 들고 있는 아이템을 인벤세이브권으로 설정합니다.";
    }

    @Override
    public String getKoUsage() {
        return "";
    }

    @Override
    public String getPermission(CommandSender sender) {
        return "create";
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        FileConfiguration config = ArcInventorySaveTicket.getInstance().getConfig();
        ConfigurationSection ticketItemSec = config.getConfigurationSection("item-manage");

        MessageContext messageContext = MessageContext.getInstance();
        Player player = (Player) sender;


        if (args.length == 1) {
            ItemStack itemStack = player.getInventory().getItemInMainHand().clone();
            itemStack.setAmount(1);

            if (itemStack.getType().equals(Material.AIR)) {
                messageContext.get(MessageType.MAIN, "air_item_in_hand").send(sender);
                return;
            }

            ticketItemSec.set("ticket-item", itemStack);
            ArcInventorySaveTicket.getInstance().saveConfig();

            messageContext.get(MessageType.MAIN, "create_ticket").send(sender);
        } else {
            messageContext.get(MessageType.ERROR, "wrong_command").send(sender);
        }

    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }
}
