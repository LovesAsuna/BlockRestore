package org.sct.BlockRestore.commands.sub;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sct.BlockRestore.gui.main;
import org.sct.easylib.util.function.command.SubCommand;

import java.util.Map;

/**
 * @author LovesAsuna
 * @date 2020/5/19 19:07
 */

public class Main implements SubCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        main main = new main();
        main.openInventory((Player) sender);
        return true;
    }

    @Override
    public Map<Integer, String[]> getParams() {
        return null;
    }
}
