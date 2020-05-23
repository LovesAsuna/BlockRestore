package org.sct.blockrestore.listener

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.sct.blockrestore.BlockRestore
import org.sct.blockrestore.data.BlockRestoreData
import org.sct.blockrestore.data.BlockRestoreData.inputTime
import org.sct.blockrestore.enumeration.SetupStatus
import org.sct.blockrestore.gui.SetupGUI
import org.sct.easylib.util.BasicUtil

class AsyncplayerchatListener : Listener {
    private val playerChat = BlockRestoreData.playerChat
    private val playerStatus: Map<Player, SetupStatus> = BlockRestoreData.playerStatus
    private var inputMaterial = BlockRestoreData.inputMaterial

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
                        openInventory(e, SetupGUI.Type.DEFAULT)
                    }
                    SetupStatus.REPLACENAME -> {
                        openInventory(e, SetupGUI.Type.BLOCK_MODIFY)
                    }
                    SetupStatus.RESTORETIME -> {
                        openInventory(e, SetupGUI.Type.TIME_MODIFY)
                    }
                }
            }
        }
    }

    private fun openInventory(e: AsyncPlayerChatEvent, type: SetupGUI.Type) {

        val player = e.player
        /*判断输入是否有效*/
        when (type) {
            SetupGUI.Type.DEFAULT, SetupGUI.Type.BLOCK_MODIFY -> {
                inputMaterial = Material.getMaterial(e.message.toUpperCase())
                if (inputMaterial == null) {
                    player.sendMessage("§c你输入的命名空间有误,请重新输入!")
                } else {
                    playerChat.remove(player)
                    SetupGUI.material = inputMaterial as Material
                    Bukkit.getScheduler().runTaskLater(BlockRestore.instance, Runnable { SetupGUI.openInventory(player, type) }, 0L)
                }
            }
            SetupGUI.Type.TIME_MODIFY -> {
                playerChat.remove(player)
                inputTime = BasicUtil.ExtraceInt(e.message)
                Bukkit.getScheduler().runTaskLater(BlockRestore.instance, Runnable { SetupGUI.openInventory(player, type) }, 0L)
            }
        }

        e.isCancelled = true
    }
}