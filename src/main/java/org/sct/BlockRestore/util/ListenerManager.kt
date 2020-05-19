package org.sct.BlockRestore.util

import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.sct.BlockRestore.Main
import org.sct.BlockRestore.listener.AsyncplayerchatListener
import org.sct.BlockRestore.listener.BlockBreakListener
import org.sct.BlockRestore.listener.BlockPlaceListener
import org.sct.BlockRestore.listener.InventoryClickListener

object ListenerManager {
    private fun register(listener: Listener) {
        Bukkit.getPluginManager().registerEvents(listener, Main.instance)
    }

    /**
     * 注册监听器
     */
    fun register() {
        register(AsyncplayerchatListener())
        register(BlockBreakListener())
        register(BlockPlaceListener())
        register(InventoryClickListener())
    }
}