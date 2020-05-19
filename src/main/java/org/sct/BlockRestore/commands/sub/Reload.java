package org.sct.BlockRestore.commands.sub;

import org.bukkit.command.CommandSender;
import org.sct.BlockRestore.Main;
import org.sct.BlockRestore.enumeration.LangType;
import org.sct.BlockRestore.file.Config;
import org.sct.BlockRestore.file.Lang;
import org.sct.easylib.util.function.command.SubCommand;

import java.util.Map;

public class Reload implements SubCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(Lang.getString(LangType.LANG_NOPERMISSION));
        }

        Lang.loadLang();
        Main.instance.saveDefaultConfig();
        Config.loadConfig();

        sender.sendMessage(Lang.getString(LangType.LANG_RELOAD));
        return true;
    }

    @Override
    public Map<Integer, String[]> getParams() {
        return null;
    }

}
