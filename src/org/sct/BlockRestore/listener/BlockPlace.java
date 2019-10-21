package org.sct.BlockRestore.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import static org.sct.BlockRestore.Manager.StaticManager.*;

public class BlockPlace implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        for (String blockname: placelist) {
            if (event.getBlock().getType() == Material.getMaterial(blockname) && getInstance().getConfig().getBoolean("denyplace")) {
                event.setCancelled(true);
            }
        }

    }
}
