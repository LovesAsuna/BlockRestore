package org.sct.blockrestore.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.sct.blockrestore.data.BlockRestoreData;

public class SetupGUI {
    private static String blockName;
    private int time;

    /**
     * 填充容器
     *
     * @param inventory 待填充的容器
     * @return void
     **/
    private static void setBlockSetup(Inventory inventory) {
        int[] slot = {0, 1, 2, 3, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
        ItemStack LIME_STAINED_GLASS_PANE = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        inventory.setItem(4, new ItemStack(Material.getMaterial(blockName)));
        for (int Slot : slot) {
            inventory.setItem(Slot, LIME_STAINED_GLASS_PANE);
        }
        inventory.setItem(10, Blocks.RED_WOOL.getItemStack());
        inventory.setItem(11, Blocks.STONE.getItemStack());
        inventory.setItem(12, Blocks.GRASS_BLOCK.getItemStack());
        inventory.setItem(13, Blocks.ENCHANTING_TABLE.getItemStack());
        inventory.setItem(14, Blocks.CHEST.getItemStack());
        inventory.setItem(15, Blocks.REDSTONE.getItemStack());
        inventory.setItem(16, Blocks.LEVER.getItemStack());
    }

    /**
     * 打开默认容器
     *
     * @param player 打开容器的玩家
     * @return void
     **/
    public static void openInventory(Player player) {
        Inventory blockSetup = BlockRestoreData.INSTANCE.setBlockSetup(3 * 9, "§b方块设置");
        setBlockSetup(blockSetup);
        player.openInventory(blockSetup);
    }

    public void openinv_modifyblock(Player player, String replace) {//打开修改方块类型后的容器
        Inventory inventory = BlockRestoreData.INSTANCE.getInvsetup();
        ItemStack it = new ItemStack(Material.getMaterial(replace));
        ItemMeta itm = it.getItemMeta();
        itm.setDisplayName("§b替换的方块类型");
        it.setItemMeta(itm);
        inventory.setItem(11, it);
        player.openInventory(inventory);
    }

    public void openinv_modifytime(Player player) {//打开修改时间后的容器
        Inventory inventory = BlockRestoreData.INSTANCE.getInvsetup();
        ItemStack it = inventory.getItem(15);
        ItemMeta itm = it.getItemMeta();
        time = BlockRestoreData.INSTANCE.getTime();
        itm.setDisplayName("§a恢复时长(" + time + "秒)");
        it.setItemMeta(itm);
        inventory.setItem(15, it);
        player.openInventory(inventory);
    }

    public void clean() {
        Inventory inventory = BlockRestoreData.INSTANCE.getInvsetup();
        inventory = null;
    }

    public String getBlockname() {
        return blockName;
    }

    public void setBlockname(String blockname) {
        this.blockName = blockname;
    }
}