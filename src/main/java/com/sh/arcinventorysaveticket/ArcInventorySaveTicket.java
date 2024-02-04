package com.sh.arcinventorysaveticket;

import com.sh.arcinventorysaveticket.command.InventorySaveTicketCommand;
import com.sh.arcinventorysaveticket.listener.PlayerDeathListener;
import com.sh.arcinventorysaveticket.message.MessageContext;
import com.sh.arcinventorysaveticket.nms.version.VersionController;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class ArcInventorySaveTicket extends JavaPlugin {

    @Getter
    private static ArcInventorySaveTicket instance;

    @Override
    public void onEnable() {
        instance = this;
        /* --------------- ITEM NBT ---------------*/
        VersionController.initialize(this);

        /* --------------- CONFIG --------------- */
        saveDefaultConfig();

        MessageContext.getInstance().initialize(getConfig());

        /* --------------- COMMAND --------------- */
        new InventorySaveTicketCommand(this);

        /* --------------- LISTENER --------------- */
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
