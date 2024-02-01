package com.sh.arcinventorysaveticket;

import com.sh.arcinventorysaveticket.command.InventorySaveTicketCommand;
import com.sh.arcinventorysaveticket.listener.PlayerDeathListener;
import com.sh.arcinventorysaveticket.message.MessageContext;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class ArcInventorySaveTicket extends JavaPlugin {

    @Getter
    private static ArcInventorySaveTicket instance;

    @Override
    public void onEnable() {
        instance = this;
        /* --------------- CONFIG --------------- */
        saveDefaultConfig();

        MessageContext.getInstance().initialize(getConfig());

        /* --------------- COMMAND --------------- */
        new InventorySaveTicketCommand(this);

        /* --------------- LISTENER --------------- */
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
