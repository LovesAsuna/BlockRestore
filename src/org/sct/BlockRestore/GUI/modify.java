package org.sct.BlockRestore.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.sct.BlockRestore.Main.variableManager;

public class modify {
    String blockname;
    Inventory blocksetup = variableManager.getBlocksetup();
    int time = variableManager.gettime();

    public void openInventory(Player player) {//基本容器
        int slot[] = {0, 1, 2, 3, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
        ItemStack LIME_STAINED_GLASS_PANE = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        blocksetup.setItem(4, new ItemStack(Material.getMaterial(blockname)));
        for (int Slot : slot) {
            blocksetup.setItem(Slot, LIME_STAINED_GLASS_PANE);
        }
        player.openInventory(blocksetup);
    }

    public void openinv(Player player, String blockname) {//带顶部图标的容器扩展
        if (this.blockname == null) this.blockname = blockname;
        blocksetup = Bukkit.createInventory(null, 3 * 9, "§bBlocksetup: §e" + blockname);
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
        blocksetup.setItem(10, Row10);
        blocksetup.setItem(11, Row11);
        blocksetup.setItem(12, Row12);
        blocksetup.setItem(13,Row13);
        blocksetup.setItem(14,Row14);
        blocksetup.setItem(15, Row15);
        blocksetup.setItem(16,Row16);
        openInventory(player);
    }

    public void openinv_2(Player player, String replace) {
        ItemStack it = new ItemStack(Material.getMaterial(replace));
        ItemMeta itm = it.getItemMeta();
        itm.setDisplayName("§b替换的方块类型");
        it.setItemMeta(itm);
        blocksetup.setItem(11,it);
        player.sendMessage(blockname);
        openInventory(player);
    }

    public void openinv_3(Player player) {
        ItemStack it = blocksetup.getItem(15);
        ItemMeta itm = it.getItemMeta();
        itm.setDisplayName("§a恢复时长(" + time + "秒)");
        it.setItemMeta(itm);
        blocksetup.setItem(15,it);
        openInventory(player);
    }
}
