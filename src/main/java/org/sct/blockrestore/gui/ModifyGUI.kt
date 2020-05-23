package org.sct.blockrestore.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.sct.blockrestore.BlockRestore;
import org.sct.blockrestore.data.BlockRestoreData;

public class modify {
    private static String blockname;
    private Inventory invmodify;
    private int time;

    private void setModify () {//填充容器
        int slot[] = {0, 1, 2, 3, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
        ItemStack LIME_STAINED_GLASS_PANE = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        invmodify.setItem(4, new ItemStack(Material.getMaterial(blockname)));
        for (int Slot : slot) {
            invmodify.setItem(Slot, LIME_STAINED_GLASS_PANE);
        }
    }

    public void openinv(Player player, String blockname) {//打开默认容器
        this.blockname = blockname;
        BlockRestoreData.INSTANCE.createModify(3 * 9, "§a编辑器");
        invmodify = BlockRestoreData.INSTANCE.getModify();
        setModify();
        ItemStack row10,row11,row12,row13,row14,row15,row16;
        if (BlockRestore.instance.getConfig().getBoolean("blocks." + blockname + ".replace")) {
            row10 = Blocks.LIME_WOOL.getItemStack();
        } else {
            row10 = Blocks.RED_WOOL.getItemStack();
        }
        row11 = new ItemStack(Material.getMaterial(BlockRestore.instance.getConfig().getString("blocks." + blockname + ".replaceblock")));
        if (BlockRestore.instance.getConfig().getBoolean("blocks." + blockname + "denyplace")) {
            row12 = Blocks.BARRIER.getItemStack();
        } else {
            row12 = Blocks.GRASS_BLOCK.getItemStack();
        }
        if (BlockRestore.instance.getConfig().getBoolean("blocks." + blockname + ".restore")) {
            row13 = Blocks.ENCHANTING_TABLE.getItemStack();
        } else {
            row13 = Blocks.CRAFTING_TABLE.getItemStack();
        }
        if (BlockRestore.instance.getConfig().getBoolean("blocks." + blockname + ".directgiveitem")) {
            row14 = Blocks.CHEST.getItemStack();
        } else {
            row14 = Blocks.ENDER_CHEST.getItemStack();
        }
        row15 = new ItemStack(Material.REDSTONE);
        row16 = Blocks.LEVER.getItemStack();
        ItemMeta row15m = row15.getItemMeta();
        ItemMeta row16m = row16.getItemMeta();
        row15m.setDisplayName("§a恢复时长(" + BlockRestore.instance.getConfig().getInt("blocks." + blockname + ".restoretime") + "秒)");
        row15.setItemMeta(row15m);
        invmodify.setItem(10,row10);
        invmodify.setItem(11,row11);
        invmodify.setItem(12,row12);
        invmodify.setItem(13,row13);
        invmodify.setItem(14,row14);
        invmodify.setItem(15,row15);
        invmodify.setItem(16,row16);
        player.openInventory(invmodify);
    }

    public void openinv_modifyblock(Player player, String replace) {//打开修改方块类型后的容器
        invmodify = BlockRestoreData.INSTANCE.getModify();
        ItemStack it = new ItemStack(Material.getMaterial(replace));
        ItemMeta itm = it.getItemMeta();
        itm.setDisplayName("§b替换的方块类型");
        it.setItemMeta(itm);
        invmodify.setItem(11,it);
        player.openInventory(invmodify);
    }

    public void openinv_modifytime(Player player) {//打开修改时间后的容器
        invmodify = BlockRestoreData.INSTANCE.getModify();
        ItemStack it = invmodify.getItem(15);
        ItemMeta itm = it.getItemMeta();
        time = BlockRestoreData.INSTANCE.getInputTime();
        itm.setDisplayName("§a恢复时长(" + time + "秒)");
        it.setItemMeta(itm);
        invmodify.setItem(15,it);
        player.openInventory(invmodify);
    }

    public void clean() {
        invmodify = BlockRestoreData.INSTANCE.getModify();
        invmodify = null;
    }

    public String getBlockname() {
        return blockname;
    }

    public void setBlockname(String blockname) {
        this.blockname = blockname;
    }
}