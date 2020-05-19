package org.sct.BlockRestore.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import java.util.ArrayList;

import static org.sct.BlockRestore.Main.*;

public class BlockPlace implements Listener {
    private ArrayList<String> blocklist = variableManager.getblocklist();

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        for (String blockname: blocklist) {
            if (e.getBlock().getType() == Material.getMaterial(blockname) && getInstance().getConfig().getBoolean("blocks." + blockname + ".denyplace")) {
                e.setCancelled(true);
            }
        }

    }
}
