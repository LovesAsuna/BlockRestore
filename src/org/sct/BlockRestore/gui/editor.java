package org.sct.BlockRestore.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;

import static org.sct.BlockRestore.Main.variableManager;
import static org.sct.BlockRestore.manager.VariableManager.getInstance;

public class editor {
    Inventory inveditor = variableManager.getInveditor();
    private ArrayList<String> blocklist = variableManager.getblocklist();

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
        ItemStack glass = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        inveditor = Bukkit.createInventory(null,5 * 9,"editor");
        int slot[] = {0,1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,37,38,39,40,41,42,43,44};
        for (int Slot : slot) {
               inveditor.setItem(Slot, glass);
        }

        int s = 10;
        for (String block: blocklist) {
            if (s == 16) s = 19;
            if (s == 25) s = 28;
            ItemStack itemStack = new ItemStack(Material.getMaterial(block));
            ItemMeta itemMeta = itemStack.getItemMeta();
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§breplace: " + status("blocks." + block + ".replace"));
            lore.add("§breplaceblock: §e" + getInstance().getConfig().getString("blocks." + block + ".replaceblock"));
            lore.add("§bdenyplace: " + status("blocks." + block + ".denyplace"));
            lore.add("§brestore: " + status("blocks." + block + ".restore"));
            lore.add("§bdirectgiveitem: " + status("blocks." + block + ".directgiveitem"));
            lore.add("§brestore: §e" + getInstance().getConfig().getInt("blocks." + block + ".restoretime"));
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            inveditor.setItem(s,itemStack);
            s++;
        }
    }

    private String status(String path) {
        if (getInstance().getConfig().getBoolean(path)) {
            return "§atrue";
        } else {
            return "§cfalse";
        }
    }
}

/*
样板:
STONE:
    replace: true
    replaceblock: GLASS
    denyplace: false
    restore: true
    directgiveitem: true
    restoretime: 1
 */