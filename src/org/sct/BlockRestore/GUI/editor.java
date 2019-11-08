package org.sct.BlockRestore.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static org.sct.BlockRestore.Manager.StaticManager.*;

public class editor {
    private Inventory editor;

    public void openInventory(Player player) {
        createInventory();
        player.openInventory(editor);
    }

    private int getnum() {
        int num = 0;
        for (String test : blocklist) {
            num++;
        }
        return num;
    }

    private Inventory createInventory() {
        int num = getnum();
        System.out.println("num: " + num);
        ItemStack glass = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        if (num > 0 && num <=7) {
            editor = Bukkit.createInventory(null,3 * 9,"Editor");
            int slot[] = {18,19,20,21,22,23,24,25,26};
            for (int Slot : slot) {
                editor.setItem(Slot,glass);
            }
        } else if (num <= 14) {
            editor = Bukkit.createInventory(null,4 * 9,"Editor");
            int slot[] = {18,26,27,28,29,30,31,32,33,34,35};
            for (int Slot : slot) {
                editor.setItem(Slot,glass);
            }
        } else {
            editor = Bukkit.createInventory(null,5 * 9,"Editor");
            int slot[] = {18,26,27,35,36,37,38,39,40,41,42,43,44};
            for (int Slot : slot) {
                editor.setItem(Slot,glass);
            }
        }
        int slot[] = {0,1,2,3,4,5,6,7,8,9,17};
        for (int Slot : slot) {
            editor.setItem(Slot,glass);
        }


        int s=10;
        for (String block: blocklist) {
            ItemStack itemStack = new ItemStack(Material.getMaterial(block));
            editor.setItem(s,itemStack);
            s++;
            //todo 修改s使其自动变换位置
        }




        return editor;
    }

}
