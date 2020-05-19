package org.sct.BlockRestore.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.sct.BlockRestore.Main.*;

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
        variableManager.setModify(3 * 9, "§a编辑器");
        invmodify = variableManager.getInvmodify();
        setModify();
        ItemStack row10,row11,row12,row13,row14,row15,row16;
        if (getInstance().getConfig().getBoolean("blocks." + blockname + ".replace")) {
            row10 = blocks.LIME_WOOL.getItemStack();
        } else {
            row10 = blocks.RED_WOOL.getItemStack();
        }
        row11 = new ItemStack(Material.getMaterial(getInstance().getConfig().getString("blocks." + blockname + ".replaceblock")));
        if (getInstance().getConfig().getBoolean("blocks." + blockname + "denyplace")) {
            row12 = blocks.BARRIER.getItemStack();
        } else {
            row12 = blocks.GRASS_BLOCK.getItemStack();
        }
        if (getInstance().getConfig().getBoolean("blocks." + blockname + ".restore")) {
            row13 = blocks.ENCHANTING_TABLE.getItemStack();
        } else {
            row13 = blocks.CRAFTING_TABLE.getItemStack();
        }
        if (getInstance().getConfig().getBoolean("blocks." + blockname + ".directgiveitem")) {
            row14 = blocks.CHEST.getItemStack();
        } else {
            row14 = blocks.ENDER_CHEST.getItemStack();
        }
        row15 = new ItemStack(Material.REDSTONE);
        row16 = blocks.LEVER.getItemStack();
        ItemMeta row15m = row15.getItemMeta();
        ItemMeta row16m = row16.getItemMeta();
        row15m.setDisplayName("§a恢复时长(" + getInstance().getConfig().getInt("blocks." + blockname + ".restoretime") + "秒)");
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
        invmodify = variableManager.getInvmodify();
        ItemStack it = new ItemStack(Material.getMaterial(replace));
        ItemMeta itm = it.getItemMeta();
        itm.setDisplayName("§b替换的方块类型");
        it.setItemMeta(itm);
        invmodify.setItem(11,it);
        player.openInventory(invmodify);
    }

    public void openinv_modifytime(Player player) {//打开修改时间后的容器
        invmodify = variableManager.getInvmodify();
        ItemStack it = invmodify.getItem(15);
        ItemMeta itm = it.getItemMeta();
        time = variableManager.gettime();
        itm.setDisplayName("§a恢复时长(" + time + "秒)");
        it.setItemMeta(itm);
        invmodify.setItem(15,it);
        player.openInventory(invmodify);
    }

    public void clean() {
        invmodify = variableManager.getInvmodify();
        invmodify = null;
    }

    public String getBlockname() {
        return blockname;
    }

    public void setBlockname(String blockname) {
        this.blockname = blockname;
    }
}