package org.sct.blockrestore.commands.sub;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.sct.blockrestore.gui.MainGUI;

import java.util.ArrayList;
import java.util.List;


public class blockrestore implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("blockrestore")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("main")) {
                MainGUI.openInventory((Player) sender);
                return true;
            }

        }
        sender.sendMessage("§7[§eBlockRestore§7]§c你输入的命令不正确");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        ArrayList<String> completions = new ArrayList<>();
        if (args.length == 1) {
            completions.add("reload");
            completions.add("main");
            return StringUtil.copyPartialMatches(args[0], completions, new ArrayList<>());
        }
        return completions;
    }

}
