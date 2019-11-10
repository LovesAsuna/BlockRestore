package org.sct.BlockRestore.listener.guilistener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class invmodify implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (e.getWhoClicked().getOpenInventory().getTitle().equalsIgnoreCase("Editor")) {



            e.setCancelled(true);
        }
    }
}
