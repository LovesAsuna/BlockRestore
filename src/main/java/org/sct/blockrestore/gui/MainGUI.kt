package org.sct.blockrestore.gui

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

object MainGUI {
    @JvmStatic
    fun openInventory(player: Player) {
        val slot = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26)
        val main = Bukkit.createInventory(null, 3 * 9, "§bBlockRestore主菜单")
        val LIME_STAINED_GLASS_PANE = ItemStack(Material.WHITE_STAINED_GLASS_PANE)
        val restonetorch = ItemStack(Material.REDSTONE_TORCH)
        val painting = ItemStack(Material.PAINTING)
        val feather = ItemStack(Material.FEATHER)
        val redstonetorchm = restonetorch.itemMeta
        val paintingm = painting.itemMeta
        val featherm = feather.itemMeta
        paintingm!!.setDisplayName("§a编辑方块")
        redstonetorchm!!.setDisplayName("§b添加方块")
        featherm!!.setDisplayName("§e修改方块")
        restonetorch.itemMeta = redstonetorchm
        painting.itemMeta = paintingm
        feather.itemMeta = featherm
        for (Slot in slot) {
            main.setItem(Slot, LIME_STAINED_GLASS_PANE)
        }
        main.setItem(12, painting)
        main.setItem(13, restonetorch)
        main.setItem(14, feather)
        player.openInventory(main)
    }
}