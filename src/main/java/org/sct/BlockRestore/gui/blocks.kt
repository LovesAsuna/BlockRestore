package org.sct.BlockRestore.gui

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

enum class blocks(val itemStack: ItemStack) {
    RED_WOOL(ItemStack(Material.RED_WOOL)),
    STONE(ItemStack(Material.STONE)),
    GRASS_BLOCK(ItemStack(Material.GRASS_BLOCK)),
    ENCHANTING_TABLE(ItemStack(Material.ENCHANTING_TABLE)),
    CHEST(ItemStack(Material.CHEST)),
    REDSTONE(ItemStack(Material.REDSTONE)),
    LEVER(ItemStack(Material.LEVER)),
    LIME_WOOL(ItemStack(Material.LIME_WOOL)),
    BARRIER(ItemStack(Material.BARRIER)),
    CRAFTING_TABLE(ItemStack(Material.CRAFTING_TABLE)),
    ENDER_CHEST(ItemStack(Material.ENDER_CHEST));

    private fun setItemMeta(item: ItemStack) {
        var itemMeta = item.itemMeta
        var name: String? = null
        when (item.type) {
            Material.RED_WOOL -> name = "§c关闭方块替换"
            Material.STONE -> name = "§b替换的方块类型"
            Material.GRASS_BLOCK -> name = "§b允许放置"
            Material.ENCHANTING_TABLE -> name = "§2自动恢复"
            Material.CHEST -> name = "§a直接给予物品"
            Material.REDSTONE -> name = "§a恢复时长(单位:秒)"
            Material.LEVER -> name = "§2完成设置"
            Material.LIME_WOOL -> name = "§2开启方块替换"
            Material.BARRIER -> name = "§c禁止放置"
            Material.CRAFTING_TABLE -> name = "§c禁用自动恢复"
            Material.ENDER_CHEST -> name = "§c禁用直接给予物品"
        }
        itemMeta?.setDisplayName(name)
        item.itemMeta = itemMeta
    }

    init {
        setItemMeta(itemStack)
    }
}