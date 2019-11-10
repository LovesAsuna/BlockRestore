package org.sct.BlockRestore.listener.guilistener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.sct.BlockRestore.gui.modify;

public class editorlistener implements Listener {
    private modify modify = new modify();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getWhoClicked().getOpenInventory().getTitle().equalsIgnoreCase("编辑器")) {
            e.getWhoClicked().sendMessage(e.getWhoClicked().getOpenInventory().getTitle());
            if (e.getRawSlot() >= 10 && e.getRawSlot()<= 16 || e.getRawSlot() >= 19 && e.getRawSlot() <= 25 || e.getRawSlot() >= 28 && e.getRawSlot() <= 34) {
                if (e.getClickedInventory().getItem(e.getRawSlot()) != null) {
                    String blockname = e.getClickedInventory().getItem(e.getRawSlot()).getType().name();
                    modify.clean();
                    modify.setBlockname(blockname);
                    player.closeInventory();
                    modify.openinv((Player) e.getWhoClicked(),blockname);
                }
            }
            e.setCancelled(true);
        }
    }
}
