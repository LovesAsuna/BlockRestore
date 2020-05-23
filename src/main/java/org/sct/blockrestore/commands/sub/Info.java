package org.sct.blockrestore.commands.sub;

import org.bukkit.command.CommandSender;
import org.sct.blockrestore.BlockRestore;
import org.sct.easylib.util.function.command.SubCommand;

import java.util.Map;

public class Info implements SubCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        sender.sendMessage("§7┌ §ePlugin§7:§b BlockRestore");
        sender.sendMessage("§7├ §eAuthor§7:§b 冰星");
        sender.sendMessage("§7├ §eVersion§7:§b " + BlockRestore.instance.getDescription().getVersion());
        sender.sendMessage("§7└ §eLink§7:§b none");

        return true;
    }

    @Override
    public Map<Integer, String[]> getParams() {
        return null;
    }
}
