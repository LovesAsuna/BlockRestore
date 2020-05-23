package org.sct.blockrestore

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.sct.blockrestore.commands.SubCommandHandler
import org.sct.blockrestore.data.BlockRestoreData
import org.sct.blockrestore.util.ListenerManager

class BlockRestore : JavaPlugin() {
    val blocklist = BlockRestoreData.blocklist
    override fun onEnable() {
        instance = this
        initialize()
        ListenerManager.register()
        server.consoleSender.sendMessage("§7[§eBlockRestore§7]§2插件已加载")
    }

    override fun onDisable() {
        server.consoleSender.sendMessage("§7[§eBlockRestore§7]§c插件已卸载")
    }

    fun initialize() {
        saveDefaultConfig()
        reloadConfig()
        readconfig()
        Bukkit.getPluginCommand("blockrestore")!!.setExecutor(SubCommandHandler(this, "blockrestore"))
    }

    private fun readconfig() {
        blocklist.clear()
        if (config.getConfigurationSection("blocks") == null) return
        for (block in config.getConfigurationSection("blocks")!!.getKeys(true)) {
            if (!block.contains(".")) {
                blocklist.add(block)
            }
        }
    }

    companion object {
        lateinit var instance : BlockRestore
    }
}