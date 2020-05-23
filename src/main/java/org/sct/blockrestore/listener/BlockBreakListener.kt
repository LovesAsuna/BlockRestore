package org.sct.blockrestore.listener

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.sct.blockrestore.BlockRestore
import org.sct.blockrestore.data.BlockRestoreData.blockList
import org.sct.blockrestore.data.BlockRestoreData.location
import org.sct.blockrestore.data.BlockRestoreData.lt_mr
import org.sct.blockrestore.data.BlockRestoreData.lt_time
import org.sct.blockrestore.util.Timer

class BlockBreakListener : Listener {
    private var timer: Timer? = null

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        if (timer == null) {
            timer = Timer()
            timer!!.run()
        }
        val lt = event.block.location
        val mr = event.block.type
        for (block in blockList) { //遍历配置中的方块类型
            val material = Material.getMaterial(block) //获得对应的方块类型
            if (mr == material) { //如果破坏方块处与配置中的方块类型匹配
                lt_mr[lt] = mr //存入破坏方块的位置
                lt_time[lt] = System.currentTimeMillis() / 1000 //存入破坏时的方块时间
                location.add(lt) //向location的Arraylist存入破坏方块的坐标
                if (BlockRestore.instance.config.getBoolean("blocks.$block.directgiveitem")) {
                    val itemStackList = event.block.drops
                    event.block.type = Material.AIR
                    for (itemStack in itemStackList) {
                        event.player.inventory.addItem(itemStack)
                    }
                }
                if (BlockRestore.instance.config.getBoolean("blocks.$mr.replace")) { //替换方块
                    Bukkit.getScheduler().runTaskLater(BlockRestore.instance, Runnable { lt.block.type = Material.getMaterial(BlockRestore.instance.config.getString("blocks.$mr.replaceblock")!!)!! }, 2L) //微延时替换
                }
            }
        }
    }
}