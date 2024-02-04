package com.sh.arcinventorysaveticket.command.sub;

import com.sh.arcinventorysaveticket.ArcInventorySaveTicket;
import com.sh.arcinventorysaveticket.command.SubCommand;
import com.sh.arcinventorysaveticket.manage.ItemManager;
import com.sh.arcinventorysaveticket.message.MessageContext;
import com.sh.arcinventorysaveticket.message.MessageType;
import com.sh.arcinventorysaveticket.nms.tank.NmsItemStackUtil;
import com.sh.arcinventorysaveticket.nms.wrapper.NBTTagCompoundWrapper;
import com.sh.arcinventorysaveticket.nms.wrapper.NmsItemWrapper;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

        MessageContext messageContext = MessageContext.getInstance();
        Player player = (Player) sender;

        ItemManager.giveTicket(player);

        messageContext.get(MessageType.MAIN, "get_ticket").send(sender);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }
}
