package org.sct.blockrestore.util

import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.sct.blockrestore.BlockRestore
import org.sct.blockrestore.listener.AsyncplayerchatListener
import org.sct.blockrestore.listener.BlockBreakListener
import org.sct.blockrestore.listener.BlockPlaceListener
import org.sct.blockrestore.listener.InventoryClickListener

object ListenerManager {
    private fun register(listener: Listener) {
        Bukkit.getPluginManager().registerEvents(listener, BlockRestore.instance)
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