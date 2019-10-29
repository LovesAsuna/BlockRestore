package org.sct.BlockRestore.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.sct.BlockRestore.GUI.blocksetup;

import static org.sct.BlockRestore.Manager.StaticManager.*;

public class AsyncPlayerChat implements Listener {
    public static Material inputmr;

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (player_chat.get(player) != null && player_chat.get(player) ) {//启动了聊天抑制
            if (e.getMessage().equalsIgnoreCase("cancel")) {
                player_chat.remove(player);
                return;
            } else {
                inputmr = Material.getMaterial(e.getMessage());
                if (inputmr == null) {//判断输入是否有效
                    player.sendMessage("§c你输入的命名空间有误,请重新输入!");
                } else {
                    player_chat.remove(player);
                    Bukkit.getScheduler().runTaskLater(getInstance(),()->{
                        new blocksetup().openinv(player,e.getMessage());
                    },0L);
                }
                e.setCancelled(true);
            }
        }

        if (player_chat_2.get(player) != null && player_chat_2.get(player)) {//启动了聊天抑制2
            if (e.getMessage().equalsIgnoreCase("cancel")) {
                player_chat_2.remove(player);
                return;
            } else {
                inputmr = Material.getMaterial(e.getMessage());
                if (inputmr == null) {//判断输入是否有效
                    player.sendMessage("§c你输入的命名空间有误,请重新输入!");
                } else {
                    player_chat_2.remove(player);
                    Bukkit.getScheduler().runTaskLater(getInstance(),()->{
                        new blocksetup().openinv_2(player,e.getMessage());
                    },0L);
                }
                e.setCancelled(true);
            }
        }
    }


}
