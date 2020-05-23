package org.sct.blockrestore.commands.sub;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sct.blockrestore.gui.MainGUI;
import org.sct.easylib.util.function.command.SubCommand;

import java.util.Map;

/**
 * @author LovesAsuna
 * @date 2020/5/19 19:07
 */

public class Gui implements SubCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        MainGUI.openInventory((Player) sender);
        return true;
    }

    @Override
    public Map<Integer, String[]> getParams() {
        return null;
    }
}
