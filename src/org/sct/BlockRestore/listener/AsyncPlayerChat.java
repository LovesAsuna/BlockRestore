package org.sct.BlockRestore.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.sct.BlockRestore.GUI.blocksetup;
import java.util.HashMap;

import static org.sct.BlockRestore.Main.variableManager;
import static org.sct.BlockRestore.Manager.VariableManager.*;

public class AsyncPlayerChat implements Listener {
    private HashMap<Player,Boolean> player_chat = variableManager.getplayer_chat();
    private HashMap<Player,Integer> player_int = variableManager.getplayer_int();
    private Material inputmr = variableManager.getInputmr();
    private blocksetup blocksetup = new blocksetup();

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (player_chat.get(player) != null && player_chat.get(player) ) {//启动了聊天抑制
            if (e.getMessage().equalsIgnoreCase("cancel")) {
                player_chat.remove(player);
                e.setCancelled(true);
                return;
            } else {
                inputmr = Material.getMaterial(e.getMessage());
                if (inputmr == null) {//判断输入是否有效
                    player.sendMessage("§c你输入的命名空间有误,请重新输入!");
                } else {
                    player_chat.remove(player);
                    Bukkit.getScheduler().runTaskLater(getInstance(),()->{
                        if (player_int.get(player) == 1) {
                            blocksetup.openinv(player,e.getMessage());
                            player_int.remove(player);
                        } else if (player_int.get(player) == 2){
                            blocksetup.openinv_modifyblock(player,e.getMessage());
                        } else {
                            blocksetup.openinv_modifytime(player);
                        }
                    },0L);
                }
                e.setCancelled(true);
            }
        }
    }
}
