package org.sct.BlockRestore.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class main {
    public void openInventory (Player player) {
        int slot[] = {0,1,2,3,4,5,6,7,8,9,17,18,19,20,21,22,23,24,25,26};
        Inventory main = Bukkit.createInventory(null,3 * 9,"BlockRestore主菜单");
        ItemStack LIME_STAINED_GLASS_PANE = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemStack restonetorch = new ItemStack(Material.REDSTONE_TORCH);
        ItemStack painting = new ItemStack(Material.PAINTING);
        ItemMeta redstonetorchm = restonetorch.getItemMeta();
        ItemMeta paintingm = painting.getItemMeta();
        paintingm.setDisplayName("§a编辑方块");
        redstonetorchm.setDisplayName("§b增加方块");
        restonetorch.setItemMeta(redstonetorchm);
        painting.setItemMeta(paintingm);
        for (int Slot : slot) {
            main.setItem(Slot,LIME_STAINED_GLASS_PANE);
        }
        main.setItem(12,painting);
        main.setItem(13,restonetorch);
        player.openInventory(main);
    }





}
