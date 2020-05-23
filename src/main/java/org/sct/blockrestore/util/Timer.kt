package org.sct.blockrestore.util

import org.bukkit.Bukkit
import org.bukkit.Material
import org.sct.blockrestore.BlockRestore
import org.sct.blockrestore.BlockRestore.Companion.instance
import org.sct.blockrestore.data.BlockRestoreData.blockList
import org.sct.blockrestore.data.BlockRestoreData.locationList
import org.sct.blockrestore.data.BlockRestoreData.locationMaterial
import org.sct.blockrestore.data.BlockRestoreData.locationTime

class Timer {
    fun run() {
        Bukkit.getScheduler().runTaskTimer(instance, Runnable {
            val nowTime = System.currentTimeMillis() / 1000
            for (lt in locationList) {
                /*是否跳过坐标*/
                var skip: Boolean
                /*此刻坐标的材质*/
                val material = locationMaterial[lt]

                /*判断此时位置的材质是否在列表里*/
                skip = blockList.parallelStream().anyMatch {
                    material == Material.getMaterial(it)
                }

                /*如果不在列表内,跳过此处坐标*/
                if (skip) {
                    continue
                }
                val restoreTime = instance.config.getInt("Blocks.$material.RestoreTime")
                val replaceBlock = Material.getMaterial(instance.config.getString("Blocks.$material.ReplaceBlock")!!)
                if (locationTime[lt] != null) {
                    /*时间大于预设时间*/
                    if (nowTime - locationTime[lt]!! + 1 >= restoreTime) {
                        /*如果ReplaceRestore开启*/
                        if (instance.config.getBoolean("Blocks.$material.Replace")) {
                            when (lt.block.type) {
                                /*如果等于空气,恢复成替换方块*/
                                Material.AIR -> {
                                    lt.block.type = replaceBlock!!
                                    locationTime[lt] = System.currentTimeMillis() / 1000
                                }
                                /*如果等于替换方块,恢复成原始方块*/
                                replaceBlock -> {
                                    lt.block.type = locationMaterial[lt]!!
                                    locationTime.remove(lt)
                                    locationMaterial.remove(lt)
                                    locationList.remove(lt)
                                }
                            }
                        } else {
                            /*ReplaceRestore关闭*/
                            if (lt.block.type == Material.AIR) { //如果等于空气,恢复成原始方块
                                lt.block.type = locationMaterial[lt]!!
                                locationTime.remove(lt)
                                locationMaterial.remove(lt)
                                locationList.remove(lt)
                            }
                        }
                    }
                }
            }
        }, 0L, 20L) //循环1秒执行检测
    }
}