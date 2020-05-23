package org.sct.blockrestore

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.sct.blockrestore.commands.SubCommandHandler
import org.sct.blockrestore.data.BlockRestoreData.blockList
import org.sct.blockrestore.util.ListenerManager

class BlockRestore : JavaPlugin() {
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
        readConfig()
        Bukkit.getPluginCommand("blockrestore")!!.setExecutor(SubCommandHandler(this, "blockrestore"))
    }

    companion object {
        lateinit var instance: BlockRestore
        fun readConfig() {
            blockList.clear()
            val configSection = instance.config.getConfigurationSection("blocks")
            if (configSection == null) {
                return
            } else {
                for (block in configSection.getKeys(true)) {
                    if (!block.contains(".")) {
                        blockList.add(block)
                    }
                }
            }
        }
    }
}