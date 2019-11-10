package org.sct.BlockRestore.listener.guilistener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import java.util.HashMap;

import static org.sct.BlockRestore.Main.variableManager;

public class invmain implements Listener {
    private HashMap<Player,Boolean> player_chat = variableManager.getplayer_chat();
    private HashMap<Player,Integer> player_int = variableManager.getplayer_int();

    @EventHandler
    public void onInvmain(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();
        if (e.getWhoClicked().getOpenInventory().getTitle().equalsIgnoreCase("BlockRestore主菜单")) {
            if (e.getRawSlot() == 12) {

            }

            if (e.getRawSlot() == 13) {
                player_chat.put(player,true);
                player_int.put(player,1);
                player.sendMessage("§b请输入添加物品的命名空间");
                player.sendMessage("§e或输入cancel取消添加");
                player.closeInventory();
            }


            e.setCancelled(true);
        }
    }
}
