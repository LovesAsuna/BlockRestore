package org.sct.BlockRestore.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.sct.BlockRestore.Main.variableManager;
import static org.sct.BlockRestore.manager.VariableManager.*;

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
        variableManager.setModify(3 * 9, "modify");
        invmodify = variableManager.getInvmodify();
        setModify();
        ItemStack Row10;
        if (getInstance().getConfig().getBoolean("blocks." + blockname + ".replace")) {
            Row10 = new ItemStack(Material.LIME_WOOL);
            ItemMeta Row10m = Row10.getItemMeta();
            Row10m.setDisplayName("§a开启方块替换");
        } else {
            Row10 = new ItemStack(Material.RED_WOOL);
            ItemMeta Row10m = Row10.getItemMeta();
            Row10m.setDisplayName("§c关闭方块替换");
        }
        ItemStack Row11 = new ItemStack(Material.getMaterial(getInstance().getConfig().getString("blocks." + blockname + ".replaceblock")));
        ItemStack Row12;
        if (getInstance().getConfig().getBoolean("blocks." + blockname + "denyplace")) {
            Row12 = new ItemStack(Material.REDSTONE_BLOCK);
            ItemMeta Row12m = Row12.getItemMeta();
            Row12m.setDisplayName("§c禁止放置");
        } else {
            Row12 = new ItemStack(Material.GRASS_BLOCK);
            ItemMeta Row12m = Row12.getItemMeta();
            Row12m.setDisplayName("§a允许放置");
        }
        ItemStack Row13;
        if (getInstance().getConfig().getBoolean("blocks." + blockname + ".restore")) {
            Row13 = new ItemStack(Material.ENCHANTING_TABLE);
            ItemMeta Row13m = Row13.getItemMeta();
            Row13m.setDisplayName("§a自动恢复");
        } else {
            Row13 = new ItemStack(Material.END_PORTAL_FRAME);
            ItemMeta Row13m = Row13.getItemMeta();
            Row13m.setDisplayName("§c禁用自动恢复");
        }
        ItemStack Row14;
        if (getInstance().getConfig().getBoolean("blocks." + blockname + ".directgiveitem")) {
            Row14 = new ItemStack(Material.CHEST);
            ItemMeta Row14m = Row14.getItemMeta();
            Row14m.setDisplayName("§a直接给予物品");
        } else {
            Row14 = new ItemStack(Material.ENDER_CHEST);
            ItemMeta Row14m = Row14.getItemMeta();
            Row14m.setDisplayName("§c禁用直接给予物品");
        }
        ItemStack Row15 = new ItemStack(Material.REDSTONE);
        ItemStack Row16 = new ItemStack(Material.LEVER);
        ItemMeta Row11m = Row10.getItemMeta();
        ItemMeta Row12m = Row12.getItemMeta();
        ItemMeta Row13m = Row13.getItemMeta();
        ItemMeta Row14m = Row14.getItemMeta();
        ItemMeta Row15m = Row15.getItemMeta();
        ItemMeta Row16m = Row16.getItemMeta();
        Row15m.setDisplayName("§a恢复时长(" + getInstance().getConfig().getInt("blocks." + blockname + ".restoretime") + "秒)");
        Row15.setItemMeta(Row15m);
        Row16m.setDisplayName("§2完成设置");
        Row16.setItemMeta(Row16m);
        invmodify.setItem(10, Row10);
        invmodify.setItem(11, Row11);
        invmodify.setItem(12, Row12);
        invmodify.setItem(13,Row13);
        invmodify.setItem(14,Row14);
        invmodify.setItem(15, Row15);
        invmodify.setItem(16,Row16);
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