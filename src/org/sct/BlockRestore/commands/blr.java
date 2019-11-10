package org.sct.BlockRestore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.sct.BlockRestore.gui.editor;
import org.sct.BlockRestore.gui.main;

import java.util.ArrayList;
import java.util.List;

import static org.sct.BlockRestore.manager.VariableManager.*;

public class blr implements CommandExecutor,TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("blr")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                if (sender.isOp()) {
                    getInstance().initialize();
                    sender.sendMessage("§7[§eBlockRestore§7]§2插件重载成功");
                } else {
                    sender.sendMessage("§7[§eBlockRestore§7]§c你没有此命令的权限");
                }
                return true;
            } else if (args.length == 1 && args[0].equalsIgnoreCase("setup")) {
                main main = new main();
                main.openInventory((Player) sender);
                return true;
            } else if (args.length == 1 && args[0].equalsIgnoreCase("test")) {
                editor editor = new editor();
                editor.openInventory((Player) sender);
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
            completions.add("setup");
            return StringUtil.copyPartialMatches(args[0],completions,new ArrayList<>());
        }
        return completions;
    }

}
