package org.sct.BlockRestore.Manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import static org.sct.BlockRestore.Manager.StaticManager.*;

public class Timer {

    public void run() {
        Bukkit.getScheduler().runTaskTimer(getInstance(),()->{
            Long nowTime = System.currentTimeMillis()/1000;
            System.out.println(nowTime);
            for (Location lt : location) {
                System.out.println("lt_time: " + lt_time.get(lt));//debug信息
                System.out.println(nowTime - lt_time.get(lt) + 1);//debug信息
                if (lt_time.get(lt) != null) {
                    if (nowTime - lt_time.get(lt) + 1 >= getInstance().getConfig().getLong("delay")) {//时间大于delay
                        System.out.println("时间大于delay");
                        if (getInstance().getConfig().getBoolean("replace")) {//如果replacerestore开启
                            if (lt.getBlock().getType() == Material.AIR) {//如果等于空气,恢复成替换方块
                                System.out.println("如果等于空气,恢复成替换方块");//debug信息
                                lt.getBlock().setType(mr);
                                lt_time.put(lt,System.currentTimeMillis()/1000);
                            } else if (lt.getBlock().getType() == mr) {//如果等于替换方块,恢复成原始方块
                                System.out.println("如果等于替换方块,恢复成原始方块");//debug信息
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
