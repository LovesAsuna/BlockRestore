package org.sct.BlockRestore.listener.guilistener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.sct.BlockRestore.gui.modify;
import java.util.HashMap;

import static org.sct.BlockRestore.Main.variableManager;
import static org.sct.BlockRestore.manager.VariableManager.getInstance;

public class modifylistener implements Listener {
    private HashMap<Player,Boolean> player_chat = variableManager.getplayer_chat();
    private HashMap<Player,Integer> player_int = variableManager.getplayer_int();
    private Inventory modify;
    private int time ;
    private modify invmodify = new modify();
    String blockname;

    @EventHandler
    public void onInvmodify(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        blockname = invmodify.getBlockname();
        modify = variableManager.getModify();
        if (e.getWhoClicked().getOpenInventory().getTitle().equalsIgnoreCase("§b方块修改: §e" + blockname)) {
            e.getWhoClicked().sendMessage(e.getWhoClicked().getOpenInventory().getTitle());
            if (e.getRawSlot() == 10) {
                if (modify.getItem(10).getType() == Material.LIME_WOOL) {
                    ItemStack Row10 = new ItemStack(Material.RED_WOOL);
                    ItemMeta Row10m = Row10.getItemMeta();
                    Row10m.setDisplayName("§c关闭方块替换");
                    Row10.setItemMeta(Row10m);
                    modify.setItem(10,Row10);
                } else {
                    ItemStack Row10 = new ItemStack(Material.LIME_WOOL);
                    ItemMeta Row10m = Row10.getItemMeta();
                    Row10m.setDisplayName("§a开启方块替换");
                    Row10.setItemMeta(Row10m);
                    modify.setItem(10,Row10);
                }
            }

            if (e.getRawSlot() == 11) {
                player_chat.put(player,true);
                player_int.put(player,22);
                player.sendMessage("§b请输入替换物品的命名空间");
                player.sendMessage("§e或输入Cancel取消添加");
                player.closeInventory();
            }

            if (e.getRawSlot() == 12) {
                if (modify.getItem(12).getType() == Material.GRASS_BLOCK) {
                    ItemStack Row12 = new ItemStack(Material.REDSTONE_BLOCK);
                    ItemMeta Row12m = Row12.getItemMeta();
                    Row12m.setDisplayName("§c禁止放置");
                    Row12.setItemMeta(Row12m);
                    modify.setItem(12,Row12);
                } else {
                    ItemStack Row12 = new ItemStack(Material.GRASS_BLOCK);
                    ItemMeta Row12m = Row12.getItemMeta();
                    Row12m.setDisplayName("§a允许放置");
                    Row12.setItemMeta(Row12m);
                    modify.setItem(12,Row12);
                }
            }

            if (e.getRawSlot() == 13) {
                if (modify.getItem(13).getType() == Material.ENCHANTING_TABLE) {
                    ItemStack Row13 = new ItemStack(Material.END_PORTAL_FRAME);
                    ItemMeta Row13m = Row13.getItemMeta();
                    Row13m.setDisplayName("§c禁用自动恢复");
                    Row13.setItemMeta(Row13m);
                    modify.setItem(13,Row13);
                } else {
                    ItemStack Row13 = new ItemStack(Material.ENCHANTING_TABLE);
                    ItemMeta Row13m = Row13.getItemMeta();
                    Row13m.setDisplayName("§a自动恢复");
                    Row13.setItemMeta(Row13m);
                    modify.setItem(13,Row13);
                }
            }

            if (e.getRawSlot() == 14) {
                if (modify.getItem(14).getType() == Material.CHEST) {
                    ItemStack Row14 = new ItemStack(Material.ENDER_CHEST);
                    ItemMeta Row14m = Row14.getItemMeta();
                    Row14m.setDisplayName("§c禁用直接给予物品");
                    Row14.setItemMeta(Row14m);
                    modify.setItem(14,Row14);
                } else {
                    ItemStack Row14 = new ItemStack(Material.CHEST);
                    ItemMeta Row14m = Row14.getItemMeta();
                    Row14m.setDisplayName("§a直接给予物品");
                    Row14.setItemMeta(Row14m);
                    modify.setItem(14,Row14);
                }
            }

            if (e.getRawSlot() == 15) {
                player_chat.put(player,true);
                player_int.put(player,33);
                player.sendMessage("§b请输入恢复时长");
                player.sendMessage("§e或输入Cancel取消添加");
                player.closeInventory();
            }

            if (e.getRawSlot() == 16) {
                time = variableManager.gettime();
                if (time == -1) {
                    player.sendMessage("§c尚未定义时间!");
                    e.setCancelled(true);
                    return;
                }
                String blockname = modify.getItem(4).getType().name();
                if (modify.getItem(10).getType() == Material.RED_WOOL) {
                    getInstance().getConfig().set("blocks." + blockname + ".replace",false);
                } else getInstance().getConfig().set("blocks." + blockname + ".replace",true);
                getInstance().getConfig().set("blocks." + blockname + ".replaceblock",modify.getItem(11).getType().name());
                if (modify.getItem(12).getType() == Material.GRASS_BLOCK) {
                    getInstance().getConfig().set("blocks." + blockname + ".denyplace",false);
                } else getInstance().getConfig().set("blocks." + blockname + ".denyplace",true);
                if (modify.getItem(13).getType() == Material.ENCHANTING_TABLE) {
                    getInstance().getConfig().set("blocks." + blockname + ".restore",true);
                } else getInstance().getConfig().set("blocks." + blockname + ".redstore",false);
                if (modify.getItem(14).getType() == Material.CHEST) {
                    getInstance().getConfig().set("blocks." + blockname + ".directgiveitem",true);
                } else getInstance().getConfig().set("blocks." + blockname + ".directgiveitem",false);
                getInstance().getConfig().set("blocks." + blockname + ".restoretime",time);
                getInstance().saveConfig();
                player.closeInventory();
                variableManager.settime(-1);
                modify = null;
                player.sendMessage("§7[§eBlockRestore§7]§2方块设置成功");
                getInstance().initialize();
            }
            e.setCancelled(true);
        }
    }
}
