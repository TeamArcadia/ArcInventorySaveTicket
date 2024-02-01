package com.sh.arcinventorysaveticket.listener;

import com.sh.arcinventorysaveticket.ArcInventorySaveTicket;
import com.sh.arcinventorysaveticket.message.MessageContext;
import com.sh.arcinventorysaveticket.message.MessageType;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.GameRule;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeathListener implements Listener {
    public void onPlayerDeath(PlayerDeathEvent event) {

        Player player = event.getEntity();

        FileConfiguration config = ArcInventorySaveTicket.getInstance().getConfig();
        ConfigurationSection ticketItemSec = config.getConfigurationSection("ticket-item");

        ItemStack savedItemSec = ticketItemSec.getItemStack("ticket-item");

        MessageContext messageContext = MessageContext.getInstance();

        if (!event.getEntity().getWorld().getGameRuleValue(GameRule.KEEP_INVENTORY)) {
            for (ItemStack item : player.getInventory().getContents()) {
                if (item.isSimilar(savedItemSec)){
                    event.getDrops().clear();

                    messageContext.get(MessageType.MAIN, "use_ticket").send(player);
                    break;
                }
            }
        }
    }
}
