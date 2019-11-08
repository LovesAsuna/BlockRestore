package org.sct.BlockRestore.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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

        Location lt = event.getBlock().getLocation();
        Material mr = event.getBlock().getType();
        for (String block : blocklist) {//遍历配置中的方块类型
            Material material = Material.getMaterial(block);//获得对应的方块类型
            if (mr == material) {//如果破坏方块处与配置中的方块类型匹配
                lt_mr.put(lt,mr);//存入破坏方块的位置
                lt_time.put(lt,System.currentTimeMillis()/1000);//存入破坏时的方块时间
                location.add(lt);//向location的Arraylist存入破坏方块的坐标

                if (getInstance().getConfig().getBoolean("blocks." + mr + ".replace")) {//替换方块
                    Bukkit.getScheduler().runTaskLater(getInstance(),()->{
                        lt.getBlock().setType(Material.getMaterial(getInstance().getConfig().getString("blocks." + mr + ".replaceblock")));

                    },2L);//微延时替换

                }

            }
        }

    }
}
