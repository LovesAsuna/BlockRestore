package org.sct.BlockRestore.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.sct.BlockRestore.Manager.StaticManager.*;

public class blocksetup {
    String blockname;
    public void openInventory (Player player) {//基本容器
        int slot[] = {0,1,2,3,5,6,7,8,9,17,18,19,20,21,22,23,24,25,26};
        ItemStack LIME_STAINED_GLASS_PANE = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        blocksetup.setItem(4,new ItemStack(Material.getMaterial(blockname)));
        for (int Slot : slot) {
            blocksetup.setItem(Slot,LIME_STAINED_GLASS_PANE);
        }
        player.openInventory(blocksetup);
    }

    public void openinv (Player player,String blockname) {//带顶部图标的容器扩展
        if (this.blockname == null) this.blockname = blockname;
        player.sendMessage(this.blockname);
        blocksetup = Bukkit.createInventory(null,3 * 9,"§bBlocksetup: §e" + blockname);
        ItemStack Row10 = new ItemStack(Material.RED_WOOL);
        ItemMeta Row10m = Row10.getItemMeta();
        Row10m.setDisplayName("§c关闭方块替换");
        Row10.setItemMeta(Row10m);
        blocksetup.setItem(10,Row10);
        ItemStack Row11 = new ItemStack(Material.STONE);
        ItemMeta Row11m = Row10.getItemMeta();
        Row10m.setDisplayName("§b替换的方块类型");
        Row10.setItemMeta(Row10m);
        blocksetup.setItem(11,Row11);
        openInventory(player);
    }

    public void openinv_2(Player player,String replace) {
        blocksetup.setItem(11,new ItemStack(Material.getMaterial(replace)));
        player.sendMessage(blockname);
        player.openInventory(blocksetup);
    }








    public Material getRow10() {
        ItemStack it = blocksetup.getItem(10);
        return it.getType();
    }

    public void setRow10(ItemStack itemStack) {
        blocksetup.setItem(10,itemStack);
    }

    public Inventory getBlocksetup() {
        return blocksetup;
    }

    public void setRow11(ItemStack itemStack) {
        blocksetup.setItem(11,itemStack);
    }
}

/*
    ItemStack blockitem = new ItemStack(getInputmr());
    ItemStack Row10 = new ItemStack(Material.LIME_WOOL);
    ItemStack Row11 = new ItemStack(Material.STONE);ItemMeta Row11m = Row11.getItemMeta();Row11m.setDisplayName("§b替换的方块类型");Row11.setItemMeta(Row11m);

 */