package org.sct.BlockRestore.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.sct.BlockRestore.Main;
import org.sct.BlockRestore.data.BlockRestoreData;

import java.util.List;


public class blockPlaceListener implements Listener {
    private List<String> blocklist = BlockRestoreData.INSTANCE.getBlocklist();

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        for (String blockname: blocklist) {
            if (e.getBlock().getType() == Material.getMaterial(blockname) && Main.instance.getConfig().getBoolean("blocks." + blockname + ".denyplace")) {
                e.setCancelled(true);
            }
        }

    }
}
