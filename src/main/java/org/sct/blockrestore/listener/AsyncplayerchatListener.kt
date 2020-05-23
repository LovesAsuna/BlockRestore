package org.sct.blockrestore.listener

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.sct.blockrestore.BlockRestore
import org.sct.blockrestore.data.BlockRestoreData
import org.sct.blockrestore.enumeration.SetupStatus
import org.sct.blockrestore.gui.SetupGUI
import org.sct.blockrestore.gui.modify

class AsyncplayerchatListener : Listener {
    private val playerChat = BlockRestoreData.playerChat
    private val playerStatus: Map<Player, SetupStatus> = BlockRestoreData.playerStatus
    private var inputMaterial = BlockRestoreData.inputMaterial
    private val modify = modify()

    @EventHandler
    fun onPlayerChat(e: AsyncPlayerChatEvent) {
        val player = e.player
        if (playerChat.getOrDefault(player, false)) {
            if ("cancel".equals(e.message, ignoreCase = true)) {
                playerChat.remove(player)
                player.sendMessage("§2操作取消成功!")
                e.isCancelled = true
            } else {
                when (playerStatus[player]) {
                    SetupStatus.ADDNAME -> {
                        inputMaterial = Material.getMaterial(e.message)
                        /*判断输入是否有效*/
                        if (inputMaterial == null) {
                            player.sendMessage("§c你输入的命名空间有误,请重新输入!")
                        } else {
                            playerChat.remove(player)
                            Bukkit.getScheduler().runTaskLater(BlockRestore.instance, Runnable { SetupGUI.openInventory(player) }, 0L)
                        }
                        e.isCancelled = true
                    }
                }
            }
        }
    }
}