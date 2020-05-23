package org.sct.blockrestore.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.sct.blockrestore.BlockRestore;
import org.sct.blockrestore.data.BlockRestoreData;

import java.util.ArrayList;
import java.util.List;


public class editor {
    Inventory inveditor = BlockRestoreData.INSTANCE.getInveditor();
    private List<String> blocklist = BlockRestoreData.INSTANCE.getBlockList();

    public void openInventory(Player player) {
        createInventory();
        player.openInventory(inveditor);
    }

    private int getnum() {
        int num = 0;
        for (String test : blocklist) {
            num++;
        }
        return num;
    }

    private void createInventory() {
        int num = getnum();
        ItemStack glass = new ItemStack(Material.REDSTONE_BLOCK);
        inveditor = Bukkit.createInventory(null, 5 * 9, "§e方块总览");
        int slot[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};
        for (int Slot : slot) {
            inveditor.setItem(Slot, glass);
        }

        int s = 10;
        for (String block : blocklist) {
            if (s == 16) s = 19;
            if (s == 25) s = 28;
            ItemStack itemStack = new ItemStack(Material.getMaterial(block));
            ItemMeta itemMeta = itemStack.getItemMeta();
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§breplace: " + status("blocks." + block + ".replace"));
            lore.add("§breplaceblock: §e" + BlockRestore.instance.getConfig().getString("blocks." + block + ".replaceblock"));
            lore.add("§bdenyplace: " + status("blocks." + block + ".denyplace"));
            lore.add("§brestore: " + status("blocks." + block + ".restore"));
            lore.add("§bdirectgiveitem: " + status("blocks." + block + ".directgiveitem"));
            lore.add("§brestore: §e" + BlockRestore.instance.getConfig().getInt("blocks." + block + ".restoretime"));
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            inveditor.setItem(s, itemStack);
            s++;
        }
    }

    private String status(String path) {
        if (BlockRestore.instance.getConfig().getBoolean(path)) {
            return "§atrue";
        } else {
            return "§cfalse";
        }
    }
}
