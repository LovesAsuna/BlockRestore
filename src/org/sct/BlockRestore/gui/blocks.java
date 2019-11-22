package org.sct.BlockRestore.gui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum blocks {
    RED_WOOL(new ItemStack(Material.RED_WOOL)),
    STONE(new ItemStack(Material.STONE)),
    GRASS_BLOCK(new ItemStack(Material.GRASS_BLOCK)),
    ENCHANTING_TABLE(new ItemStack(Material.ENCHANTING_TABLE)),
    CHEST(new ItemStack(Material.CHEST)),
    REDSTONE(new ItemStack(Material.REDSTONE)),
    LEVER(new ItemStack(Material.LEVER)),
    LIME_WOOL(new ItemStack(Material.LIME_WOOL)),
    BARRIER(new ItemStack(Material.BARRIER)),
    CRAFTING_TABLE(new ItemStack(Material.CRAFTING_TABLE)),
    ENDER_CHEST(new ItemStack(Material.ENDER_CHEST));

    private ItemStack itemStack;

    private blocks(ItemStack itemStack) {
        this.itemStack = itemStack;
        setItemMeta(this.itemStack);
    }

    private void setItemMeta(ItemStack itemStack) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        if (itemStack.getType() == Material.RED_WOOL) {
            itemMeta.setDisplayName("§c关闭方块替换");
            this.itemStack.setItemMeta(itemMeta);
        } else if(itemStack.getType() == Material.STONE) {
            itemMeta.setDisplayName("§b替换的方块类型");
            this.itemStack.setItemMeta(itemMeta);
        } else if (itemStack.getType() == Material.GRASS_BLOCK) {
            itemMeta.setDisplayName("§b允许放置");
            this.itemStack.setItemMeta(itemMeta);
        } else if (itemStack.getType() == Material.ENCHANTING_TABLE) {
            itemMeta.setDisplayName("§2自动恢复");
            this.itemStack.setItemMeta(itemMeta);
        } else if (itemStack.getType() == Material.CHEST) {
            itemMeta.setDisplayName("§a直接给予物品");
            this.itemStack.setItemMeta(itemMeta);
        } else if (itemStack.getType() == Material.REDSTONE) {
            itemMeta.setDisplayName("§a恢复时长(单位:秒)");
            this.itemStack.setItemMeta(itemMeta);
        } else if (itemStack.getType() == Material.LEVER) {
            itemMeta.setDisplayName("§2完成设置");
            this.itemStack.setItemMeta(itemMeta);
        } else if (itemStack.getType() == Material.LIME_WOOL) {
            itemMeta.setDisplayName("§2开启方块替换");
            this.itemStack.setItemMeta(itemMeta);
        } else if (itemStack.getType() == Material.BARRIER) {
            itemMeta.setDisplayName("§c禁止放置");
            this.itemStack.setItemMeta(itemMeta);
        } else if (itemStack.getType() == Material.CRAFTING_TABLE) {
            itemMeta.setDisplayName("§c禁用自动恢复");
            this.itemStack.setItemMeta(itemMeta);
        } else if (itemStack.getType() == Material.ENDER_CHEST) {
            itemMeta.setDisplayName("§c禁用直接给予物品");
            this.itemStack.setItemMeta(itemMeta);
        }
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
