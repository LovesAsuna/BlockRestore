package org.sct.blockrestore.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.sct.blockrestore.BlockRestore;
import org.sct.blockrestore.data.BlockRestoreData;
import org.sct.blockrestore.util.Timer;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;


public class BlockBreakListener implements Listener {
    private Timer timer;
    private CopyOnWriteArrayList<Location> location = BlockRestoreData.INSTANCE.getLocation();
    private Map<Location, Material> lt_mr = BlockRestoreData.INSTANCE.getLt_mr();
    private List<String> blocklist = BlockRestoreData.INSTANCE.getBlocklist();
    private Map<Location, Long> lt_time = BlockRestoreData.INSTANCE.getLt_time();

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
                lt_mr.put(lt, mr);//存入破坏方块的位置
                lt_time.put(lt, System.currentTimeMillis() / 1000);//存入破坏时的方块时间
                location.add(lt);//向location的Arraylist存入破坏方块的坐标
                if (BlockRestore.instance.getConfig().getBoolean("blocks." + block + ".directgiveitem")) {
                    Collection<ItemStack> itemStackList = event.getBlock().getDrops();
                    event.getBlock().setType(Material.AIR);
                    for (ItemStack itemStack : itemStackList) {
                        event.getPlayer().getInventory().addItem(itemStack);
                    }

                }

                if (BlockRestore.instance.getConfig().getBoolean("blocks." + mr + ".replace")) {//替换方块
                    Bukkit.getScheduler().runTaskLater(BlockRestore.instance, () -> {
                        lt.getBlock().setType(Material.getMaterial(BlockRestore.instance.getConfig().getString("blocks." + mr + ".replaceblock")));
                    }, 2L);//微延时替换
                }

            }
        }

    }
}
