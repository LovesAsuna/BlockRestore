package org.sct.blockrestore.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.sct.blockrestore.BlockRestore;
import org.sct.blockrestore.data.BlockRestoreData;

import java.util.List;


public class BlockPlaceListener implements Listener {
    private List<String> blocklist = BlockRestoreData.INSTANCE.getBlocklist();

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        for (String blockname: blocklist) {
            if (e.getBlock().getType() == Material.getMaterial(blockname) && BlockRestore.instance.getConfig().getBoolean("blocks." + blockname + ".denyplace")) {
                e.setCancelled(true);
            }
        }

    }
}
