package org.sct.blockrestore.gui

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.sct.blockrestore.BlockRestore
import org.sct.blockrestore.BlockRestore.Companion.instance
import org.sct.blockrestore.BlockRestore.Companion.readConfig
import org.sct.blockrestore.data.BlockRestoreData.blockList
import org.sct.blockrestore.data.BlockRestoreData.overview
import java.util.*

object OverViewGUI {

    fun openInventory(player: Player) {
        init()
        player.openInventory(overview)
    }

    private fun init() {
        val glass = ItemStack(Material.REDSTONE_BLOCK)
        overview = Bukkit.createInventory(null, 5 * 9, "§e方块总览")
        val slots = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44)
        for (slot in slots) {
            overview.setItem(slot, glass)
        }
        var s = 10
        readConfig()
        for (block in blockList) {
            if (s == 16) s = 19
            if (s == 25) s = 28
            val itemStack = ItemStack(Material.getMaterial(block)!!)
            val itemMeta = itemStack.itemMeta
            val lore: MutableList<String> = ArrayList()
            lore.add("§bReplace: " + status("Blocks.$block.Replace"))
            lore.add("§bReplaceBlock: §e" + instance.config.getString("Blocks.$block.ReplaceBlock"))
            lore.add("§bDenyPlace: " + status("Blocks.$block.DenyPlace"))
            lore.add("§bRestore: " + status("Blocks.$block.restore"))
            lore.add("§bDirectGiveItem: " + status("Blocks.$block.DirectGiveItem"))
            lore.add("§bRestore: §e" +instance.config.getInt("Blocks.$block.RestoreTime"))
            itemMeta!!.lore = lore
            itemStack.itemMeta = itemMeta
            overview.setItem(s, itemStack)
            s++
        }
    }

    private fun status(path: String): String {
        return if (instance.config.getBoolean(path)) {
            "§aTrue"
        } else {
            "§cFalse"
        }
    }
}