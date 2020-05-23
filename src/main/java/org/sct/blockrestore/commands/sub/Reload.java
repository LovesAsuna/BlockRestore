package org.sct.blockrestore.commands.sub;

import org.bukkit.command.CommandSender;
import org.sct.blockrestore.BlockRestore;
import org.sct.blockrestore.enumeration.LangType;
import org.sct.blockrestore.file.Config;
import org.sct.blockrestore.file.Lang;
import org.sct.easylib.util.function.command.SubCommand;

import java.util.Map;

public class Reload implements SubCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(Lang.getString(LangType.LANG_NOPERMISSION));
        }

        Lang.loadLang();
        BlockRestore.instance.saveDefaultConfig();
        Config.loadConfig();

        sender.sendMessage(Lang.getString(LangType.LANG_RELOAD));
        return true;
    }

    @Override
    public Map<Integer, String[]> getParams() {
        return null;
    }

}
