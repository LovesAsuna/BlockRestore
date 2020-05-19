package org.sct.BlockRestore.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.sct.BlockRestore.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.sct.BlockRestore.Main.variableManager;

public class Timer {
    private CopyOnWriteArrayList<Location> location = variableManager.getlocation();
    private HashMap<Location,Material> lt_mr = variableManager.getlt_mr();
    private ArrayList<String> blocklist = variableManager.getblocklist();
    private HashMap<Location,Long> lt_time = variableManager.getlt_time();

    public void run() {
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(),()->{
            Long nowTime = System.currentTimeMillis()/1000;
            for (Location lt : location) {
                boolean skip = true;//是否跳过坐标
                Material material = null;//此刻坐标的材质

                if (lt_mr.get(lt) != null) {
                    material = lt_mr.get(lt);//获取此刻坐标的材质
                }
                for (String block : blocklist) {//判断此时位置的材质是否在列表里
                    if (material == Material.getMaterial(block)) {
                        skip = false;
                    }
                }
                if (skip) continue;//如果不在列表内,跳过此处坐标
                int time = Main.getInstance().getConfig().getInt("blocks." + material + ".restoretime");
                System.out.println("blocks." + material + ".restoretime: " + time);
                Material replace = Material.getMaterial(Main.getInstance().getConfig().getString("blocks." + material + ".replaceblock"));
                if (lt_time.get(lt) != null) {
                    if (nowTime - lt_time.get(lt) + 1 >= time) {//时间大于预设时间
                        //System.out.println("时间大于delay");
                        if (Main.getInstance().getConfig().getBoolean("blocks." + material + ".replace")) {//如果replacerestore开启
                            if (lt.getBlock().getType() == Material.AIR) {//如果等于空气,恢复成替换方块
                                //System.out.println("如果等于空气,恢复成替换方块");
                                lt.getBlock().setType(replace);
                                lt_time.put(lt,System.currentTimeMillis()/1000);
                            } else if (lt.getBlock().getType() == replace) {//如果等于替换方块,恢复成原始方块
                                //System.out.println("如果等于替换方块,恢复成原始方块");
                                lt.getBlock().setType(lt_mr.get(lt));
                                lt_time.remove(lt);
                                lt_mr.remove(lt);
                                location.remove(lt);
                            }
                        } else  {//如果replacerestore关闭
                            if (lt.getBlock().getType() == Material.AIR) {//如果等于空气,恢复成原始方块
                                lt.getBlock().setType(lt_mr.get(lt));
                                lt_time.remove(lt);
                                lt_mr.remove(lt);
                                location.remove(lt);
                            }
                        }
                    }
                }
            }
        },0L,20L);//循环1秒执行检测
    }
}
