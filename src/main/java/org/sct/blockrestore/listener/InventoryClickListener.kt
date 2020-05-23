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
import org.sct.blockrestore.data.BlockRestoreData.time
import org.sct.blockrestore.gui.blocks
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
                        BlockRestoreData.player_int[player] = 1
                        BlockRestoreData.player_chat[player] = true
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
                        BlockRestoreData.player_int[e.whoClicked as Player] = 2
                        BlockRestoreData.player_chat[e.whoClicked as Player] = true
                        e.whoClicked.sendMessage("§7[§eBlockRestore§7]§b请输入替换物品的命名空间")
                        e.whoClicked.closeInventory()
                    }
                    15 -> {
                        BlockRestoreData.player_int[e.whoClicked as Player] = 3
                        BlockRestoreData.player_chat[e.whoClicked as Player] = true
                        e.whoClicked.sendMessage("§7[§eBlockRestore§7]§b请输入方块恢复的时间")
                        e.whoClicked.closeInventory()
                    }
                    16 -> {
                        val time = time
                        if (time == -1) {
                            player.sendMessage("§7[§eBlockRestore§7]§c尚未定义时间!")
                            e.isCancelled = true
                            return
                        }
                        output(e, player, time)
                        BlockRestoreData.time = -1
                        player.sendMessage("§7[§eBlockRestore§7]§2方块设置成功")
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

            /*modify*/
            title.equals("§a编辑器", true) -> {
                when (e.rawSlot) {
                    11 -> {
                        BlockRestoreData.player_int[player] = 22
                        BlockRestoreData.player_chat[player] = true
                        player.sendMessage("§7[§eBlockRestore§7]§b请输入替换物品的命名空间")
                        player.closeInventory()
                    }
                    15 -> {
                        BlockRestoreData.player_int[e.whoClicked as Player] = 33
                        BlockRestoreData.player_chat[e.whoClicked as Player] = true
                        player.sendMessage("§7[§eBlockRestore§7]§b请输入方块恢复的时间")
                        player.closeInventory()
                    }
                    16 -> {
                        val time = time
                        output(e, player, time)
                        player.sendMessage("§7[§eBlockRestore§7]§2方块修改成功")
                    }
                }
                changeBlock(e)
                e.isCancelled = true
            }

        }
    }

    private fun getItem(e: InventoryClickEvent, slot: Int): ItemStack? {
        return e.whoClicked.openInventory.getItem(slot)
    }

    private fun getItemMaterial(e: InventoryClickEvent, slot: Int): Material {
        return e.whoClicked.openInventory.getItem(slot)!!.type
    }

    private fun setItem(e: InventoryClickEvent, slot: Int, itemStack: ItemStack) {
        e.whoClicked.openInventory.setItem(slot, itemStack)
    }

    private fun changeBlock(e: InventoryClickEvent) {
        if (e.rawSlot == 10) {
            if (getItem(e, 10) == blocks.RED_WOOL.itemStack) {
                setItem(e, 10, blocks.LIME_WOOL.itemStack)
            } else {
                setItem(e, 10, blocks.RED_WOOL.itemStack)
            }
        } else if (e.rawSlot == 12) {
            if (getItem(e, 12) == blocks.GRASS_BLOCK.itemStack) {
                setItem(e, 12, blocks.BARRIER.itemStack)
            } else {
                setItem(e, 12, blocks.GRASS_BLOCK.itemStack)
            }
        } else if (e.rawSlot == 13) {
            if (getItem(e, 13) == blocks.ENCHANTING_TABLE.itemStack) {
                setItem(e, 13, blocks.CRAFTING_TABLE.itemStack)
            } else {
                setItem(e, 13, blocks.ENCHANTING_TABLE.itemStack)
            }
        } else if (e.rawSlot == 14) {
            if (getItem(e, 14) == blocks.CHEST.itemStack) {
                setItem(e, 14, blocks.ENDER_CHEST.itemStack)
            } else {
                setItem(e, 14, blocks.CHEST.itemStack)
            }
        }
    }

    private fun output(e: InventoryClickEvent, player: Player, time: Int) {
        val blockname = getItemMaterial(e, 4).name
        if (getItemMaterial(e, 10) == Material.REDSTONE_BLOCK) {
            BlockRestore.instance.config["blocks.$blockname.replace"] = false
        } else {
            BlockRestore.instance.config["blocks.$blockname.replace"] = true
        }
        BlockRestore.instance.config["blocks.$blockname.replaceblock"] = getItemMaterial(e, 11).name
        if (getItemMaterial(e, 12) == Material.GRASS) {
            BlockRestore.instance.config["blocks.$blockname.denyplace"] = false
        } else {
            BlockRestore.instance.config["blocks.$blockname.denyplace"] = true
        }
        if (getItemMaterial(e, 13) == Material.ENCHANTING_TABLE) {
            BlockRestore.instance.config["blocks.$blockname.restore"] = true
        } else {
            BlockRestore.instance.config["blocks.$blockname.redstore"] = false
        }
        if (getItemMaterial(e, 14) == Material.CHEST) {
            BlockRestore.instance.config["blocks.$blockname.directgiveitem"] = true
        } else {
            BlockRestore.instance.config["blocks.$blockname.directgiveitem"] = false
        }
        BlockRestore.instance.config["blocks.$blockname.restoretime"] = time
        BlockRestore.instance.saveConfig()
        player.closeInventory()
        BlockRestore.instance.initialize()
    }
}