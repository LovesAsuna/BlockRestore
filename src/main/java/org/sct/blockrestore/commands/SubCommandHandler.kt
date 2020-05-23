package org.sct.blockrestore.commands

import org.bukkit.plugin.java.JavaPlugin
import org.sct.blockrestore.commands.sub.Info
import org.sct.blockrestore.commands.sub.Gui
import org.sct.blockrestore.commands.sub.Reload
import org.sct.easylib.util.function.command.CommandHandler

class SubCommandHandler(instance: JavaPlugin, cmd: String?) : CommandHandler(instance, cmd) {
    init {
        registerSubCommand("info", Info())
        registerSubCommand("gui", Gui())
        registerSubCommand("reload", Reload())
    }
}