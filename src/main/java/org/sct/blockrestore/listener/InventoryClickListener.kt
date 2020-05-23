package org.sct.blockrestore.listener

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.sct.blockrestore.BlockRestore
import org.sct.blockrestore.data.BlockRestoreData
import org.sct.blockrestore.data.BlockRestoreData.inputTime
import org.sct.blockrestore.enumeration.SetupStatus
import org.sct.blockrestore.gui.Blocks
import org.sct.blockrestore.gui.editor
import org.sct.blockrestore.gui.modify

class InventoryClickListener : Listener {

    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {
        val player = e.whoClicked as Player
        val title = e.whoClicked.openInventory.title

        /*主菜单*/
        when {
            title.equals("§bBlockRestore主菜单", true) -> {
                when (e.rawSlot) {
                    13 -> {
                        BlockRestoreData.playerStatus[player] = SetupStatus.ADDNAME
                        BlockRestoreData.playerChat[player] = true
                        player.sendMessage("§7[§eBlockRestore§7]§b请输入添加物品的命名空间")
                        player.closeInventory()
                    }
                    14 -> {
                        player.closeInventory()
                        Bukkit.getScheduler().runTaskLater(BlockRestore.instance, Runnable { editor().openInventory(player) }, 1L)
                    }
                }
                e.isCancelled = true
            }

            /*setup菜单*/
            title.equals("§b方块设置", true) -> {
                changeBlock(e)
                when (e.rawSlot) {
                    11 -> {
                        BlockRestoreData.playerStatus[player] = SetupStatus.REPLACENAME
                        BlockRestoreData.playerChat[player] = true
                        player.sendMessage("§7[§eBlockRestore§7]§b请输入替换物品的命名空间")
                        player.closeInventory()
                    }
                    15 -> {
                        BlockRestoreData.playerStatus[player] = SetupStatus.RESTORETIME
                        BlockRestoreData.playerChat[player] = true
                        player.sendMessage("§7[§eBlockRestore§7]§b请输入方块恢复的时间")
                        player.closeInventory()
                    }
                    16 -> {
                        val time = inputTime
                        if (time == -1) {
                            player.sendMessage("§7[§eBlockRestore§7]§c尚未定义时间!")
                            e.isCancelled = true
                            return
                        }
                        outPut(e, player, time)
                        inputTime = -1
                        player.sendMessage("§7[§eBlockRestore§7]§2方块设置成功")
                    }
                }
                e.isCancelled = true
            }

            /*modify*/
            title.equals("§a编辑器", true) -> {
                changeBlock(e)
                when (e.rawSlot) {
                    11 -> {
                        BlockRestoreData.playerStatus[player] = SetupStatus.REPLACENAME
                        BlockRestoreData.playerChat[player] = true
                        player.sendMessage("§7[§eBlockRestore§7]§b请输入替换物品的命名空间")
                        player.closeInventory()
                    }
                    15 -> {
                        BlockRestoreData.playerStatus[e.whoClicked as Player] = SetupStatus.RESTORETIME
                        BlockRestoreData.playerChat[e.whoClicked as Player] = true
                        player.sendMessage("§7[§eBlockRestore§7]§b请输入方块恢复的时间")
                        player.closeInventory()
                    }
                    16 -> {
                        outPut(e, player, inputTime)
                        player.sendMessage("§7[§eBlockRestore§7]§2方块修改成功")
                    }
                }
                e.isCancelled = true
            }

            /*editor*/
            title.equals("§e方块总览", true) -> {
                if (e.currentItem == null) return
                if (e.currentItem!!.type != Material.REDSTONE_BLOCK) {
                    Bukkit.getScheduler().runTaskLater(BlockRestore.instance, Runnable {
                        val modify = modify()
                        modify.clean()
                        modify.openinv(player, e.currentItem!!.type.name)
                    }, 1L)
                }
                e.isCancelled = true
            }

        }
    }

    private fun getItem(e: InventoryClickEvent, slot: Int): ItemStack? {
        return e.whoClicked.openInventory.getItem(slot)
    }

    private fun getItemMaterial(e: InventoryClickEvent, slot: Int): Material {
        return getItem(e, slot)!!.type
    }

    private fun setItem(e: InventoryClickEvent, slot: Int, itemStack: ItemStack) {
        e.whoClicked.openInventory.setItem(slot, itemStack)
    }

    private fun changeBlock(e: InventoryClickEvent) {
        when (e.rawSlot) {
            10 -> {
                when (getItem(e, 10)) {
                    Blocks.RED_WOOL.itemStack -> setItem(e, 10, Blocks.LIME_WOOL.itemStack)
                    else -> setItem(e, 10, Blocks.RED_WOOL.itemStack)
                }
            }
            12 -> {
                when (getItem(e, 12)) {
                    Blocks.GRASS_BLOCK.itemStack -> setItem(e, 12, Blocks.BARRIER.itemStack)
                    else -> setItem(e, 12, Blocks.GRASS_BLOCK.itemStack)
                }
            }
            13 -> {
                when (getItem(e, 13)) {
                    Blocks.ENCHANTING_TABLE.itemStack -> setItem(e, 13, Blocks.CRAFTING_TABLE.itemStack)
                    else -> setItem(e, 13, Blocks.ENCHANTING_TABLE.itemStack)
                }
            }
            14 -> {
                when (getItem(e, 14)) {
                    Blocks.CHEST.itemStack -> setItem(e, 14, Blocks.ENDER_CHEST.itemStack)
                    else -> setItem(e, 14, Blocks.CHEST.itemStack)
                }
            }
        }
    }

    private fun outPut(e: InventoryClickEvent, player: Player, time: Int) {
        val blockName = getItemMaterial(e, 4).name
        when (getItemMaterial(e, 10)) {
            Material.REDSTONE_BLOCK -> BlockRestore.instance.config["Blocks.$blockName.Replace"] = false
            else -> BlockRestore.instance.config["Blocks.$blockName.Replace"] = true
        }

        BlockRestore.instance.config["Blocks.$blockName.ReplaceBlock"] = getItemMaterial(e, 11).name

        when (getItemMaterial(e, 12)) {
            Material.GRASS -> BlockRestore.instance.config["Blocks.$blockName.DenyPlace"] = false
            else -> BlockRestore.instance.config["Blocks.$blockName.denyplace"] = true
        }

        when (getItemMaterial(e, 13)) {
            Material.ENCHANTING_TABLE -> BlockRestore.instance.config["Blocks.$blockName.Restore"] = true
            else -> BlockRestore.instance.config["Blocks.$blockName.Restore"] = false
        }

        when (getItemMaterial(e, 14)) {
            Material.CHEST -> BlockRestore.instance.config["Blocks.$blockName.DirectGiveItem"] = true
            else -> BlockRestore.instance.config["Blocks.$blockName.DirectGiveItem"] = false
        }
        BlockRestore.instance.config["Blocks.$blockName.RestoreTime"] = time
        BlockRestore.instance.saveConfig()
        player.closeInventory()
    }
}