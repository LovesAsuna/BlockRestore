package org.sct.blockrestore.gui

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.sct.blockrestore.data.BlockRestoreData
import org.sct.blockrestore.data.BlockRestoreData.blockSetup
import org.sct.blockrestore.data.BlockRestoreData.createBlockSetup

object SetupGUI {
    private var time = 0

    lateinit var material: Material

    /**
     * 填充容器
     *
     * @param inventory 待填充的容器
     * @return void
     */
    private fun init(inventory: Inventory) {
        val slot = intArrayOf(0, 1, 2, 3, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26)
        val LIME_STAINED_GLASS_PANE = ItemStack(Material.WHITE_STAINED_GLASS_PANE)
        inventory.setItem(4, ItemStack(material))
        for (Slot in slot) {
            inventory.setItem(Slot, LIME_STAINED_GLASS_PANE)
        }
        inventory.setItem(10, Blocks.RED_WOOL.itemStack)
        inventory.setItem(11, Blocks.STONE.itemStack)
        inventory.setItem(12, Blocks.GRASS_BLOCK.itemStack)
        inventory.setItem(13, Blocks.ENCHANTING_TABLE.itemStack)
        inventory.setItem(14, Blocks.CHEST.itemStack)
        inventory.setItem(15, Blocks.REDSTONE.itemStack)
        inventory.setItem(16, Blocks.LEVER.itemStack)
    }

    /**
     * 打开容器
     *
     * @param player 打开容器的玩家
     * @return void
     */
    fun openInventory(player: Player, type : Type) {
        when (type) {
            Type.DEFAULT -> {
                val blockSetup = createBlockSetup(3 * 9, "§b方块设置")
                init(blockSetup)
                player.openInventory(blockSetup)
            }
            Type.BLOCK_MODIFY -> {
                /*打开修改方块类型后的容器*/
                val inventory = blockSetup
                val it = ItemStack(material)
                val itm = it.itemMeta
                itm!!.setDisplayName("§b替换的方块类型")
                it.itemMeta = itm
                inventory.setItem(11, it)
                player.openInventory(inventory)
            }

            Type.TIME_MODIFY -> {
                /*打开修改时间后的容器*/
                val inventory = blockSetup
                val itemStack = inventory.getItem(15)
                val itemMeta = itemStack!!.itemMeta
                time = BlockRestoreData.inputTime
                itemMeta!!.setDisplayName("§a恢复时长(" + time + "秒)")
                itemStack.itemMeta = itemMeta
                inventory.setItem(15, itemStack)
                player.openInventory(inventory)
            }

        }
    }

    enum class Type {
        DEFAULT,
        BLOCK_MODIFY,
        TIME_MODIFY;
    }

}