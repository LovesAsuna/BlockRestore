package org.sct.BlockRestore.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.sct.BlockRestore.gui.*;
import java.util.HashMap;

import static org.sct.BlockRestore.Main.variableManager;

public class guilistener implements Listener {
    private HashMap<Player,Integer> player_int = variableManager.getplayer_int();
    private HashMap<Player,Boolean> player_chat = variableManager.getplayer_chat();
    setup setup = new setup();
    modify modify = new modify();
    editor editor = new editor();


    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        //主菜单
        System.out.println("点击事件");
        if (e.getWhoClicked().getOpenInventory().getTitle().equalsIgnoreCase("§bBlockRestore主菜单")) {
            if (e.getRawSlot() == 13) {
                player_int.put((Player)e.getWhoClicked(),1);
                player_chat.put((Player)e.getWhoClicked(),true);
                e.getWhoClicked().sendMessage("请输入添加物品的命名空间");
                e.getWhoClicked().closeInventory();
            }
            e.setCancelled(true);
        }

        //setup菜单
        if (e.getWhoClicked().getOpenInventory().getTitle().equalsIgnoreCase("§b方块设置")) {


            e.setCancelled(true);
        }







    }
}
