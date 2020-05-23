package org.sct.blockrestore.listener

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.sct.blockrestore.BlockRestore.Companion.instance
import org.sct.blockrestore.data.BlockRestoreData.blockList
import org.sct.blockrestore.data.BlockRestoreData.locationList
import org.sct.blockrestore.data.BlockRestoreData.locationMaterial
import org.sct.blockrestore.data.BlockRestoreData.locationTime
import org.sct.blockrestore.util.TimerUtil

class BlockBreakListener : Listener {
    private var timerUtil: TimerUtil? = null

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        if (timerUtil == null) {
            timerUtil = TimerUtil()
            timerUtil!!.run()
        }
        val player = event.player
        val location = event.block.location
        val material = event.block.type
        /*遍历配置中的方块类型*/
        for (block in blockList) {
            /*获得对应的方块类型*/
            val blockMaterial = Material.getMaterial(block)
            /*如果破坏方块处与配置中的方块类型匹配*/
            if (material == blockMaterial) {
                /*存入破坏方块的位置*/
                locationMaterial[location] = material
                /*存入破坏时的方块时间*/
                locationTime[location] = System.currentTimeMillis() / 1000
                /*向location的Arraylist存入破坏方块的坐标*/
                locationList.add(location)
                /*直接给予物品不掉落方块*/
                if (instance.config.getBoolean("Blocks.$block.DirectGiveItem")) {
                    event.block.type = Material.AIR
                    event.block.drops.forEach {
                        player.inventory.addItem(it)
                    }
                }
                /*替换方块*/
                if (instance.config.getBoolean("Blocks.$material.Replace")) {
                    Bukkit.getScheduler().runTaskLater(instance, Runnable {
                        location.block.type = Material.getMaterial(instance.config.getString("Blocks.$material.ReplaceBlock")!!)!!
                    }, 2L) //微延时替换
                }
            }
        }
    }
}