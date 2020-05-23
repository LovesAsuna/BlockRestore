package org.sct.blockrestore.listener

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.scheduler.BukkitTask
import org.sct.blockrestore.BlockRestore
import org.sct.blockrestore.data.BlockRestoreData
import org.sct.blockrestore.data.BlockRestoreData.inputMaterial
import org.sct.blockrestore.data.BlockRestoreData.inputTime
import org.sct.blockrestore.enumeration.Status
import org.sct.blockrestore.gui.ModifyGUI
import org.sct.blockrestore.gui.SetupGUI
import org.sct.easylib.util.BasicUtil

class AsyncplayerchatListener : Listener {
    private val playerChat = BlockRestoreData.playerChat
    private val playerStatus: Map<Player, Status> = BlockRestoreData.playerStatus

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
                    Status.ADDNAME -> openInventory(e, SetupGUI.Type.DEFAULT)
                    Status.REPLACENAME -> openInventory(e, SetupGUI.Type.BLOCK_MODIFY)
                    Status.RESTORETIME -> openInventory(e, SetupGUI.Type.TIME_MODIFY)
                    Status.EDITREPLACENAME -> openInventory(e, ModifyGUI.Type.BLOCK_MODIFY)
                    Status.EDITRESTORETIME -> openInventory(e, ModifyGUI.Type.TIME_MODIFY)
                }
            }
        }
    }

    private fun openInventory(e: AsyncPlayerChatEvent, type: SetupGUI.Type) {
        val player = e.player
        /*判断输入是否有效*/
        when (type) {
            SetupGUI.Type.DEFAULT, SetupGUI.Type.BLOCK_MODIFY -> blockFunction.invoke(e, {
                Bukkit.getScheduler().runTaskLater(BlockRestore.instance, Runnable { SetupGUI.openInventory(player, type) }, 0L)
            }, player)

            SetupGUI.Type.TIME_MODIFY -> timeFunction.invoke(e, {
                Bukkit.getScheduler().runTaskLater(BlockRestore.instance, Runnable { SetupGUI.openInventory(player, type) }, 0L)
            }, player)
        }

        e.isCancelled = true
    }

    private fun openInventory(e: AsyncPlayerChatEvent, type: ModifyGUI.Type) {
        val player = e.player
        /*判断输入是否有效*/
        when (type) {
            ModifyGUI.Type.BLOCK_MODIFY -> blockFunction.invoke(e, {
                Bukkit.getScheduler().runTaskLater(BlockRestore.instance, Runnable { ModifyGUI.openInventory(player, inputMaterial!!, type) }, 0L)
            }, player)

            ModifyGUI.Type.TIME_MODIFY -> timeFunction.invoke(e, {
                Bukkit.getScheduler().runTaskLater(BlockRestore.instance, Runnable { ModifyGUI.openInventory(player, inputMaterial!!, type) }, 0L)
            }, player)

            else -> {
            }
        }

        e.isCancelled = true
    }

    private val blockFunction = { e: AsyncPlayerChatEvent, run: () -> BukkitTask, player: Player ->
        inputMaterial = Material.getMaterial(e.message.toUpperCase())
        if (inputMaterial == null) {
            player.sendMessage("§c你输入的命名空间有误,请重新输入!")
        } else {
            playerChat.remove(player)
            SetupGUI.material = inputMaterial as Material
            run.invoke()
        }
    }

    private val timeFunction = { e: AsyncPlayerChatEvent, run: () -> BukkitTask, player: Player ->
        playerChat.remove(player)
        inputTime = BasicUtil.ExtraceInt(e.message)
        run.invoke()
    }
}
