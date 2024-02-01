package com.sh.arcinventorysaveticket.listener;

import com.sh.arcinventorysaveticket.ArcInventorySaveTicket;
import com.sh.arcinventorysaveticket.message.MessageContext;
import org.bukkit.GameRule;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        Player player = event.getEntity();

        FileConfiguration config = ArcInventorySaveTicket.getInstance().getConfig();

        ItemStack itemStack = config.getItemStack("item-manage.ticket-item");

        MessageContext messageContext = MessageContext.getInstance();

        ItemStack[] inventory = player.getInventory().getContents().clone();

        /*if (event.getEntity().getWorld().getGameRuleValue(GameRule.KEEP_INVENTORY)) {
            return;
        }*/

        if (player.getInventory().contains(itemStack)) {

            event.getDrops().clear();

            ArcInventorySaveTicket.getInstance().getServer().getScheduler()
                    .runTaskLater(ArcInventorySaveTicket.getInstance(), () -> player.getInventory().setContents(inventory), 2L);

            itemStack.setAmount(itemStack.getAmount() - 1);
            //itemStack.setAmount(itemStack.getAmount() - 1);

        }

    }
}
