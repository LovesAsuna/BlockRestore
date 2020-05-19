package org.sct.BlockRestore.commands.sub;

import org.bukkit.command.CommandSender;
import org.sct.BlockRestore.Main;
import org.sct.easylib.util.function.command.SubCommand;

import java.util.Map;

public class Info implements SubCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        sender.sendMessage("§7┌ §ePlugin§7:§b BlockRestore");
        sender.sendMessage("§7├ §eAuthor§7:§b 冰星");
        sender.sendMessage("§7├ §eVersion§7:§b " + Main.instance.getDescription().getVersion());
        sender.sendMessage("§7└ §eLink§7:§b none");

        return true;
    }

    @Override
    public Map<Integer, String[]> getParams() {
        return null;
    }
}
