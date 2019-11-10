package org.sct.BlockRestore.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.List;

import static org.sct.BlockRestore.Main.variableManager;
import static org.sct.BlockRestore.manager.VariableManager.*;

public class BlockPlace implements Listener {
    private List<String> placelist = variableManager.getplacelist();

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        for (String blockname: placelist) {
            if (event.getBlock().getType() == Material.getMaterial(blockname) && getInstance().getConfig().getBoolean("denyplace")) {
                event.setCancelled(true);
            }
        }

    }
}
