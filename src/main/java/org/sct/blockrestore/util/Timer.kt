package org.sct.blockrestore.util

import org.bukkit.Bukkit
import org.bukkit.Material
import org.sct.blockrestore.BlockRestore
import org.sct.blockrestore.data.BlockRestoreData.blockList
import org.sct.blockrestore.data.BlockRestoreData.locationList
import org.sct.blockrestore.data.BlockRestoreData.locationMaterial
import org.sct.blockrestore.data.BlockRestoreData.locationTime

class Timer {
    fun run() {
        Bukkit.getScheduler().runTaskTimer(BlockRestore.instance, Runnable {
            val nowTime = System.currentTimeMillis() / 1000
            for (lt in locationList) {
                var skip = true //是否跳过坐标
                var material: Material? = null //此刻坐标的材质
                if (locationMaterial[lt] != null) {
                    material = locationMaterial[lt] //获取此刻坐标的材质
                }
                for (block in blockList) { //判断此时位置的材质是否在列表里
                    if (material == Material.getMaterial(block)) {
                        skip = false
                    }
                }
                if (skip) {
                    continue  //如果不在列表内,跳过此处坐标
                }
                val time = BlockRestore.instance.config.getInt("blocks.$material.restoretime")
                val replace = Material.getMaterial(BlockRestore.instance.config.getString("blocks.$material.replaceblock")!!)
                if (locationTime[lt] != null) {
                    if (nowTime - locationTime[lt]!! + 1 >= time) { //时间大于预设时间
                        //System.out.println("时间大于delay");
                        if (BlockRestore.instance.config.getBoolean("blocks.$material.replace")) { //如果replacerestore开启
                            if (lt.block.type == Material.AIR) { //如果等于空气,恢复成替换方块
                                //System.out.println("如果等于空气,恢复成替换方块");
                                lt.block.type = replace!!
                                locationTime[lt] = System.currentTimeMillis() / 1000
                            } else if (lt.block.type == replace) { //如果等于替换方块,恢复成原始方块
                                //System.out.println("如果等于替换方块,恢复成原始方块");
                                lt.block.type = locationMaterial[lt]!!
                                locationTime.remove(lt)
                                locationMaterial.remove(lt)
                                locationList.remove(lt)
                            }
                        } else { //如果replacerestore关闭
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