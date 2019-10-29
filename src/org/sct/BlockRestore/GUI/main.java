package org.sct.BlockRestore.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class main {
    public void openInventory (Player player) {
        int slot[] = {0,1,2,3,4,5,6,7,8,9,17,18,19,20,21,22,23,24,25,26};
        Inventory main = Bukkit.createInventory(null,3 * 9,"BlockRestore主菜单");
        ItemStack LIME_STAINED_GLASS_PANE = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemStack restonetorch = new ItemStack(Material.REDSTONE_TORCH);
        ItemMeta redstonetorchm = restonetorch.getItemMeta();
        ArrayList<String> redstonetorchlore = new ArrayList<>();
        redstonetorchlore.add("test");
        redstonetorchm.setLore(redstonetorchlore);
        redstonetorchm.setDisplayName("增加物品");
        restonetorch.setItemMeta(redstonetorchm);
        for (int Slot : slot) {
            main.setItem(Slot,LIME_STAINED_GLASS_PANE);
        }
        main.setItem(11,restonetorch);
        player.openInventory(main);
    }





}
