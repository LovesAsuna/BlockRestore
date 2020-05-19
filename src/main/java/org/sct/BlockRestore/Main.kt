package org.sct.BlockRestore

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.sct.BlockRestore.commands.blockrestore
import org.sct.BlockRestore.data.BlockRestoreData
import org.sct.BlockRestore.listener.AsyncplayerchatListener
import org.sct.BlockRestore.listener.BlockBreakListener
import org.sct.BlockRestore.listener.InventoryClickListener
import org.sct.BlockRestore.listener.blockPlaceListener

class Main : JavaPlugin() {
    val blocklist = BlockRestoreData.blocklist
    override fun onEnable() {
        instance = this
        initialize()
        registerEvents()
        server.consoleSender.sendMessage("§7[§eBlockRestore§7]§2插件已加载")
    }

    override fun onDisable() {
        server.consoleSender.sendMessage("§7[§eBlockRestore§7]§c插件已卸载")
    }

    fun initialize() {
        saveDefaultConfig()
        reloadConfig()
        readconfig()
        Bukkit.getPluginCommand("blr")!!.setExecutor(blockrestore())
    }

    private fun registerEvents() {
        val listener = arrayOf(BlockBreakListener(), blockPlaceListener(), AsyncplayerchatListener(), InventoryClickListener())
        for (Listener in listener) {
            Bukkit.getPluginManager().registerEvents(Listener, this)
        }
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
        lateinit var instance : Main
    }
}