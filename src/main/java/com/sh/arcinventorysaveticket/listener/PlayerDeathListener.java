package com.sh.arcinventorysaveticket.listener;

import com.sh.arcinventorysaveticket.ArcInventorySaveTicket;
import com.sh.arcinventorysaveticket.manage.ItemManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        ItemStack itemStack = ItemManager.getTicketItemStack();

        if (itemStack == null) {
            player.sendMessage("null");
            return;
        }

        FileConfiguration config = ArcInventorySaveTicket.getInstance().getConfig();
        ConfigurationSection ticketItemSec = config.getConfigurationSection("ticket-item");

        if (ticketItemSec == null) {
            return;
        }

        int itemIndex = -1;
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            ItemStack item = player.getInventory().getItem(i);
            if (item != null && item.getType() == itemStack.getType()) {
                itemIndex = i;
                break;
            }
        }

        if (itemIndex != -1) {
            ItemStack[] inventory = player.getInventory().getContents().clone();
            inventory[itemIndex] = null;

            event.getDrops().clear();

            ArcInventorySaveTicket.getInstance().getServer().getScheduler().runTaskLater(ArcInventorySaveTicket.getInstance(), () -> {
                player.getInventory().setContents(inventory);
            }, 1L);
        }
    }
}