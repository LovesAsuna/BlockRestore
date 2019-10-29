package org.sct.BlockRestore.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.sct.BlockRestore.GUI.blocksetup;

import static org.sct.BlockRestore.Manager.StaticManager.*;

public class InventoryClick implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getWhoClicked().getOpenInventory().getTitle().equalsIgnoreCase("BlockRestore主菜单")) {
            if (e.getRawSlot() == 11) {
                player_chat.put(player,true);
                player.sendMessage("§b请输入添加物品的命名空间");
                player.sendMessage("§e或输入Cancel取消添加");
                player.closeInventory();
            }
            e.setCancelled(true);
        }

        if (e.getWhoClicked().getOpenInventory().getTitle().contains("§bBlocksetup: §e")) {
            if (e.getRawSlot() == 10) {
                blocksetup blocksetup = new blocksetup();
                if (blocksetup.getRow10() == Material.LIME_WOOL) {
                    ItemStack Row10 = new ItemStack(Material.RED_WOOL);
                    ItemMeta Row10m = Row10.getItemMeta();
                    Row10m.setDisplayName("§c关闭方块替换");
                    Row10.setItemMeta(Row10m);
                    blocksetup.setRow10(Row10);
                } else {
                    ItemStack Row10 = new ItemStack(Material.LIME_WOOL);
                    ItemMeta Row10m = Row10.getItemMeta();
                    Row10m.setDisplayName("§a开启方块替换");
                    Row10.setItemMeta(Row10m);
                    blocksetup.setRow10(Row10);
                }
            }

            if (e.getRawSlot() == 11) {
                player_chat_2.put(player,true);
                player.sendMessage("§b请输入替换物品的命名空间");
                player.sendMessage("§e或输入Cancel取消添加");
                player.closeInventory();
            }

            e.setCancelled(true);
        }
    }
}
