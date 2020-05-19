package org.sct.BlockRestore.commands

import org.bukkit.plugin.java.JavaPlugin
import org.sct.BlockRestore.commands.sub.Info
import org.sct.BlockRestore.commands.sub.Main
import org.sct.BlockRestore.commands.sub.Reload
import org.sct.easylib.util.function.command.CommandHandler

class SubCommandHandler(instance: JavaPlugin, cmd: String?) : CommandHandler(instance, cmd) {
    init {
        registerSubCommand("info", Info())
        registerSubCommand("main", Main())
        registerSubCommand("reload", Reload())
    }
}