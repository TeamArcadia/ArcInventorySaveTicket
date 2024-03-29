package com.sh.arcinventorysaveticket.command.sub;

import com.sh.arcinventorysaveticket.ArcInventorySaveTicket;
import com.sh.arcinventorysaveticket.command.SubCommand;
import com.sh.arcinventorysaveticket.message.MessageContext;
import com.sh.arcinventorysaveticket.message.MessageType;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ReloadConfigCommand implements SubCommand {
    @Override
    public String getKoName() {
        return "리로드";
    }

    @Override
    public String getKoDescription() {
        return "플러그인의 콘피그를 리로드합니다.";
    }

    @Override
    public String getKoUsage() {
        return "";
    }

    @Override
    public String getPermission(CommandSender sender) {
        return "reload";
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        MessageContext messageContext = MessageContext.getInstance();

        if (args.length == 1) {

            ArcInventorySaveTicket.getInstance().reloadConfig();
            messageContext.initialize(ArcInventorySaveTicket.getInstance().getConfig());
            messageContext.get(MessageType.NORMAL, "reload_config").send(sender);

        } else {
            messageContext.get(MessageType.ERROR, "wrong_command").send(sender);
        }

    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
