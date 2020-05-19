package org.sct.BlockRestore.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.sct.BlockRestore.manager.Timer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.sct.BlockRestore.Main.*;

public class BlockBreak implements Listener {
    private Timer timer;
    private CopyOnWriteArrayList<Location> location = variableManager.getlocation();
    private HashMap<Location,Material> lt_mr = variableManager.getlt_mr();
    private ArrayList<String> blocklist = variableManager.getblocklist();
    private HashMap<Location,Long> lt_time = variableManager.getlt_time();

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
                if (getInstance().getConfig().getBoolean("blocks." + block + ".directgiveitem")) {
                    Collection<ItemStack> itemStackList = event.getBlock().getDrops();
                    event.getBlock().setType(Material.AIR);
                    for (ItemStack itemStack : itemStackList) {
                        event.getPlayer().getInventory().addItem(itemStack);
                    }

                }

                if (getInstance().getConfig().getBoolean("blocks." + mr + ".replace")) {//替换方块
                    Bukkit.getScheduler().runTaskLater(getInstance(),()->{
                        lt.getBlock().setType(Material.getMaterial(getInstance().getConfig().getString("blocks." + mr + ".replaceblock")));
                    },2L);//微延时替换
                }

            }
        }

    }
}
