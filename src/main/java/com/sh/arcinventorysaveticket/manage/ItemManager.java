package com.sh.arcinventorysaveticket.manage;

import com.sh.arcinventorysaveticket.ArcInventorySaveTicket;
import com.sh.arcinventorysaveticket.nms.tank.NmsItemStackUtil;
import com.sh.arcinventorysaveticket.nms.wrapper.NBTTagCompoundWrapper;
import com.sh.arcinventorysaveticket.nms.wrapper.NmsItemWrapper;
import com.sh.arcinventorysaveticket.util.ColorUtil;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemManager {

    private static FileConfiguration config = ArcInventorySaveTicket.getInstance().getConfig();
    private static  ConfigurationSection ticketItemSec = config.getConfigurationSection("ticket-item");
    public static ItemStack getTicketItemStack() {
        Material material = Material.getMaterial(ticketItemSec.getString("type"));
        String name = ColorUtil.colorize(ticketItemSec.getString("name"));
        int customModelData = ticketItemSec.getInt("custom_model_data");
        List<String> lore = ColorUtil.loreColorzie(ticketItemSec.getStringList("lore"));

        ItemStack itemStack = new ItemStack(material);

        NmsItemWrapper nmsItemWrapper = NmsItemStackUtil.getInstance().asNMSCopy(itemStack);
        NBTTagCompoundWrapper nbtTagCompoundWrapper = nmsItemWrapper.getTag();

        if (nbtTagCompoundWrapper == null) nbtTagCompoundWrapper = NmsItemStackUtil.getInstance().getNbtCompoundUtil().newInstance();
        nbtTagCompoundWrapper.setString("arcInvSaveTicket", "true");
        nmsItemWrapper.setTag(nbtTagCompoundWrapper);

        ItemMeta itemMeta = NmsItemStackUtil.getInstance().asBukkitCopy(nmsItemWrapper).getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setCustomModelData(customModelData);
        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static void giveTicket(Player player) {
        player.getInventory().addItem(getTicketItemStack());
    }

}
