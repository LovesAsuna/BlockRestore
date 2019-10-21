package org.sct.BlockRestore.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.sct.BlockRestore.Manager.Timer;

import static org.sct.BlockRestore.Manager.StaticManager.*;

public class BlockBreak implements Listener {
    Timer timer;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (timer == null) {
            timer = new Timer();
            timer.run();
        }
        for (Location location : location) {
            if (event.getBlock().getLocation() == location && location.getBlock().getType() == mr) {//如果是石头，存入新时间
                lt_time.put(location,System.currentTimeMillis()/1000);
            }
        }
        Location lt = event.getBlock().getLocation();
        Material mr = event.getBlock().getType();
        String replaceblock = getInstance().getConfig().getString("replaceblock");
        for (String blockname : breaklist) {
            if (mr == Material.getMaterial(blockname)) {
                if (replace) {//如果开启则替换
                    Bukkit.getScheduler().runTaskLater(getInstance(), () -> {
                        event.getBlock().setType(Material.getMaterial(replaceblock));
                    }, 5L);
                    lt_mr.putIfAbsent(lt, Material.getMaterial(replaceblock));//存进 位置_石头(替换方块)
                }
                location.add(lt);
                lt_mr.put(lt, mr);//存进破坏前的原始方块类型
                event.getPlayer().sendMessage(String.valueOf(lt));//debug信息
                event.getPlayer().sendMessage(String.valueOf(System.currentTimeMillis()/1000));//debug信息
                lt_time.put(lt,System.currentTimeMillis()/1000);//存入破坏时的时间
            }
        }
    }
}
