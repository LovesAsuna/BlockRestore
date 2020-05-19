package org.sct.BlockRestore.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.sct.BlockRestore.data.BlockRestoreData;

public class setup {
    private static String blockname;
    private Inventory invsetup;
    private int time;

    private void setBlocksetup () {//填充容器
        int slot[] = {0, 1, 2, 3, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
        ItemStack LIME_STAINED_GLASS_PANE = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        invsetup.setItem(4, new ItemStack(Material.getMaterial(blockname)));
        for (int Slot : slot) {
            invsetup.setItem(Slot, LIME_STAINED_GLASS_PANE);
        }
        invsetup.setItem(10,blocks.RED_WOOL.getItemStack());
        invsetup.setItem(11,blocks.STONE.getItemStack());
        invsetup.setItem(12,blocks.GRASS_BLOCK.getItemStack());
        invsetup.setItem(13,blocks.ENCHANTING_TABLE.getItemStack());
        invsetup.setItem(14,blocks.CHEST.getItemStack());
        invsetup.setItem(15,blocks.REDSTONE.getItemStack());
        invsetup.setItem(16,blocks.LEVER.getItemStack());
    }

    public void openinv(Player player, String blockname) {//打开默认容器
        this.blockname = blockname;
        BlockRestoreData.INSTANCE.setBlocksetup(3 * 9, "§b方块设置");
        invsetup = BlockRestoreData.INSTANCE.getInvsetup();
        setBlocksetup();
        player.openInventory(invsetup);
    }

    public void openinv_modifyblock(Player player, String replace) {//打开修改方块类型后的容器
        invsetup = BlockRestoreData.INSTANCE.getInvsetup();
        ItemStack it = new ItemStack(Material.getMaterial(replace));
        ItemMeta itm = it.getItemMeta();
        itm.setDisplayName("§b替换的方块类型");
        it.setItemMeta(itm);
        invsetup.setItem(11,it);
        player.openInventory(invsetup);
    }

    public void openinv_modifytime(Player player) {//打开修改时间后的容器
        invsetup = BlockRestoreData.INSTANCE.getInvsetup();
        ItemStack it = invsetup.getItem(15);
        ItemMeta itm = it.getItemMeta();
        time = BlockRestoreData.INSTANCE.getTime();
        itm.setDisplayName("§a恢复时长(" + time + "秒)");
        it.setItemMeta(itm);
        invsetup.setItem(15,it);
        player.openInventory(invsetup);
    }

    public void clean() {
        invsetup = BlockRestoreData.INSTANCE.getInvsetup();
        invsetup = null;
    }

    public String getBlockname() {
        return blockname;
    }

    public void setBlockname(String blockname) {
        this.blockname = blockname;
    }
}