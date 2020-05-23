package org.sct.blockrestore

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask
import org.sct.blockrestore.commands.SubCommandHandler
import org.sct.blockrestore.data.BlockRestoreData.blockList
import org.sct.blockrestore.data.BlockRestoreData.pool
import org.sct.blockrestore.util.ListenerManager
import org.sct.blockrestore.util.TimerUtil
import org.sct.easylib.util.plugin.CheckUpdate
import org.sct.easylib.util.plugin.FileUpdate

class BlockRestore : JavaPlugin() {
    private lateinit var task: BukkitTask
    override fun onEnable() {
        instance = this
        saveDefaultConfig()
        readConfig()
        pool.submit {
            FileUpdate.update(instance, "config.yml", dataFolder.path)
            FileUpdate.update(instance, "lang.yml", dataFolder.path)
            CheckUpdate.check(Bukkit.getConsoleSender(), instance, "LovesAsuna", "ZDRlZWY4ZDZlMzIyNDExYjk3NThlMGNiN2ZmYzg3NTRiOGIwZDUzZA==")
        }
        ListenerManager.register()
        server.consoleSender.sendMessage("§7[§eBlockRestore§7]§2插件已加载")
        Bukkit.getPluginCommand("blockrestore")!!.setExecutor(SubCommandHandler(this, "blockrestore"))
        task = TimerUtil.run()
    }

    override fun onDisable() {
        server.consoleSender.sendMessage("§7[§eBlockRestore§7]§c插件已卸载")
        task.cancel()
    }

    companion object {
        lateinit var instance: BlockRestore
        fun readConfig() {
            blockList.clear()
            val configSection = instance.config.getConfigurationSection("Blocks")
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