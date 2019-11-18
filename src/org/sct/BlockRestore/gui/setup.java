package org.sct.BlockRestore.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.sct.BlockRestore.Main.variableManager;

public class setup {
    private static String blockname;
    private Inventory invsetup;
    private int time;

    private void setBlocksetup () {//填充容器
        int slot[] = {0, 1, 2, 3, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
        ItemStack LIME_STAINED_GLASS_PANE = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        invsetup.setItem(4, new ItemStack(Material.getMaterial(blockname)));
        for (int Slot : slot) {
            invsetup.setItem(Slot, LIME_STAINED_GLASS_PANE);
        }
        ItemStack Row10 = new ItemStack(Material.RED_WOOL);
        ItemStack Row11 = new ItemStack(Material.STONE);
        ItemStack Row12 = new ItemStack(Material.GRASS_BLOCK);
        ItemStack Row13 = new ItemStack(Material.ENCHANTING_TABLE);
        ItemStack Row14 = new ItemStack(Material.CHEST);
        ItemStack Row15 = new ItemStack(Material.REDSTONE);
        ItemStack Row16 = new ItemStack(Material.LEVER);
        ItemMeta Row10m = Row10.getItemMeta();
        ItemMeta Row11m = Row10.getItemMeta();
        ItemMeta Row12m = Row12.getItemMeta();
        ItemMeta Row13m = Row13.getItemMeta();
        ItemMeta Row14m = Row14.getItemMeta();
        ItemMeta Row15m = Row15.getItemMeta();
        ItemMeta Row16m = Row16.getItemMeta();
        Row10m.setDisplayName("§c关闭方块替换");
        Row10.setItemMeta(Row10m);
        Row11m.setDisplayName("§b替换的方块类型");
        Row11.setItemMeta(Row11m);
        Row12m.setDisplayName("§b允许放置");
        Row12.setItemMeta(Row12m);
        Row13m.setDisplayName("§2自动恢复");
        Row13.setItemMeta(Row13m);
        Row14m.setDisplayName("§a直接给予物品");
        Row14.setItemMeta(Row14m);
        Row15m.setDisplayName("§a恢复时长(单位:秒)");
        Row15.setItemMeta(Row15m);
        Row16m.setDisplayName("§2完成设置");
        Row16.setItemMeta(Row16m);
        invsetup.setItem(10, Row10);
        invsetup.setItem(11, Row11);
        invsetup.setItem(12, Row12);
        invsetup.setItem(13,Row13);
        invsetup.setItem(14,Row14);
        invsetup.setItem(15, Row15);
        invsetup.setItem(16,Row16);
    }

    public void openinv(Player player, String blockname) {//打开默认容器
        this.blockname = blockname;
        variableManager.setBlocksetup(3 * 9, "§b方块设置");
        invsetup = variableManager.getInvsetup();
        setBlocksetup();
        player.openInventory(invsetup);
    }

    public void openinv_modifyblock(Player player, String replace) {//打开修改方块类型后的容器
        invsetup = variableManager.getInvsetup();
        ItemStack it = new ItemStack(Material.getMaterial(replace));
        ItemMeta itm = it.getItemMeta();
        itm.setDisplayName("§b替换的方块类型");
        it.setItemMeta(itm);
        invsetup.setItem(11,it);
        player.openInventory(invsetup);
    }

    public void openinv_modifytime(Player player) {//打开修改时间后的容器
        invsetup = variableManager.getInvsetup();
        ItemStack it = invsetup.getItem(15);
        ItemMeta itm = it.getItemMeta();
        time = variableManager.gettime();
        itm.setDisplayName("§a恢复时长(" + time + "秒)");
        it.setItemMeta(itm);
        invsetup.setItem(15,it);
        player.openInventory(invsetup);
    }

    public void clean() {
        invsetup = variableManager.getInvsetup();
        invsetup = null;
    }

    public String getBlockname() {
        return blockname;
    }

    public void setBlockname(String blockname) {
        this.blockname = blockname;
    }
}