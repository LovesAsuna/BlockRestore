package org.sct.blockrestore.gui

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.sct.blockrestore.BlockRestore.Companion.instance
import org.sct.blockrestore.data.BlockRestoreData.createModify
import org.sct.blockrestore.data.BlockRestoreData.inputTime
import org.sct.blockrestore.data.BlockRestoreData.modify

object ModifyGUI {
    private var time = 0

    private fun init(inventory: Inventory) {
        val slot = intArrayOf(0, 1, 2, 3, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26)
        val LIME_STAINED_GLASS_PANE = ItemStack(Material.LIME_STAINED_GLASS_PANE)
        for (Slot in slot) {
            inventory.setItem(Slot, LIME_STAINED_GLASS_PANE)
        }
    }

    fun openInventory(player: Player, blockMaterial: Material, type: Type) {
        when (type) {
            Type.DEFAULT -> {
                val modify = createModify(3 * 9, "§a编辑器").also {
                    it.setItem(4, ItemStack(blockMaterial))
                }

                init(modify)
                val Replace: ItemStack
                val ReplaceBlock: ItemStack
                val row12: ItemStack
                val Restore: ItemStack
                val DirectGiveItem: ItemStack
                val Time: ItemStack
                val save: ItemStack
                Replace = if (instance.config.getBoolean("Blocks.${blockMaterial.name}.Replace")) {
                    Blocks.LIME_WOOL.itemStack
                } else {
                    Blocks.RED_WOOL.itemStack
                }
                ReplaceBlock = ItemStack(Material.getMaterial(instance.config.getString("Blocks.${blockMaterial.name}.ReplaceBlock")!!)!!)
                row12 = if (instance.config.getBoolean("Blocks." + blockMaterial.name + "DenyPlace")) {
                    Blocks.BARRIER.itemStack
                } else {
                    Blocks.GRASS_BLOCK.itemStack
                }
                Restore = if (instance.config.getBoolean("Blocks.${blockMaterial.name}.Restore")) {
                    Blocks.ENCHANTING_TABLE.itemStack
                } else {
                    Blocks.CRAFTING_TABLE.itemStack
                }
                DirectGiveItem = if (instance.config.getBoolean("Blocks.${blockMaterial.name}.DirectGiveItem")) {
                    Blocks.CHEST.itemStack
                } else {
                    Blocks.ENDER_CHEST.itemStack
                }
                Time = ItemStack(Material.REDSTONE)
                save = Blocks.LEVER.itemStack
                val itemMeta = Time.itemMeta
                itemMeta!!.setDisplayName("§a恢复时长(" + instance.config.getInt("Blocks.${blockMaterial.name}.RestoreTime") + "秒)")
                Time.itemMeta = itemMeta
                modify.setItem(10, Replace)
                modify.setItem(11, ReplaceBlock)
                modify.setItem(12, row12)
                modify.setItem(13, Restore)
                modify.setItem(14, DirectGiveItem)
                modify.setItem(15, Time)
                modify.setItem(16, save)
                player.openInventory(modify)
            }
            Type.BLOCK_MODIFY -> {
                val itemStack = ItemStack(blockMaterial)
                val itemMeta = itemStack.itemMeta
                itemMeta!!.setDisplayName("§b替换的方块类型")
                itemStack.itemMeta = itemMeta
                modify.setItem(11, itemStack)
                player.openInventory(modify)
            }
            Type.TIME_MODIFY -> {
                val itemStack = modify.getItem(15)
                val itemMeta = itemStack!!.itemMeta
                time = inputTime
                itemMeta!!.setDisplayName("§a恢复时长(" + time + "秒)")
                itemStack.itemMeta = itemMeta
                modify.setItem(15, itemStack)
                player.openInventory(modify)
            }
        }

    }

    enum class Type {
        DEFAULT,
        BLOCK_MODIFY,
        TIME_MODIFY;
    }


}