package com.sh.arcinventorysaveticket.listener;

import org.bukkit.entity.Player;
import org.bukkit.GameRule;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import static com.sh.arcinventorysaveticket.command.sub.CreateTicketCommand.savedItems;

public class PlayerDeathListener implements Listener {
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if (!event.getEntity().getWorld().getGameRuleValue(GameRule.KEEP_INVENTORY)) {
            if (savedItems.containsKey(player.getUniqueId())) {
                event.getDrops().clear();

                player.getInventory().removeItem(savedItems.get(player.getUniqueId()));
            }
        }
    }
}
