package org.sct.blockrestore.util

import org.bukkit.Bukkit
import org.bukkit.Material
import org.sct.blockrestore.BlockRestore
import org.sct.blockrestore.BlockRestore.Companion.instance
import org.sct.blockrestore.data.BlockRestoreData.blockList
import org.sct.blockrestore.data.BlockRestoreData.locationList
import org.sct.blockrestore.data.BlockRestoreData.locationMaterial
import org.sct.blockrestore.data.BlockRestoreData.locationTime

object TimerUtil {
    fun run() {
        Bukkit.getScheduler().runTaskTimer(instance, Runnable {
            val nowTime = System.currentTimeMillis() / 1000
            for (location in locationList) {
                /*是否跳过坐标*/
                var skip: Boolean
                /*此刻坐标的材质*/
                val material = locationMaterial[location]

                /*判断此时位置的材质是否在列表里*/
                skip = !blockList.parallelStream().anyMatch {
                    material == Material.getMaterial(it)
                }
                /*如果不在列表内,跳过此处坐标*/
                if (skip) {
                    continue
                }
                val restoreTime = instance.config.getInt("Blocks.$material.RestoreTime")
                val replaceBlock = Material.getMaterial(instance.config.getString("Blocks.$material.ReplaceBlock")!!)
                if (locationTime[location] != null) {
                    /*时间大于预设时间*/
                    if (nowTime - locationTime[location]!! + 1 >= restoreTime) {
                        /*如果ReplaceRestore开启*/
                        if (instance.config.getBoolean("Blocks.$material.Replace")) {
                            when (location.block.type) {
                                /*如果等于空气,恢复成替换方块*/
                                Material.AIR -> {
                                    location.block.type = replaceBlock!!
                                    locationTime[location] = System.currentTimeMillis() / 1000
                                }
                                /*如果等于替换方块,恢复成原始方块*/
                                replaceBlock -> {
                                    location.block.type = locationMaterial[location]!!
                                    locationTime.remove(location)
                                    locationMaterial.remove(location)
                                    locationList.remove(location)
                                }
                                else -> {
                                    TODO("被恶意替换方块")
                                }
                            }
                        } else {
                            /*ReplaceRestore关闭*/
                            if (location.block.type == Material.AIR) { //如果等于空气,恢复成原始方块
                                location.block.type = locationMaterial[location]!!
                                locationTime.remove(location)
                                locationMaterial.remove(location)
                                locationList.remove(location)
                            }
                        }
                    }
                }
            }
        }, 0L, 20L) //循环1秒执行检测
    }
}