package org.sct.blockrestore.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.sct.blockrestore.BlockRestore;
import org.sct.blockrestore.data.BlockRestoreData;
import org.sct.blockrestore.gui.blocks;
import org.sct.blockrestore.gui.editor;
import org.sct.blockrestore.gui.modify;

import java.util.Map;


public class InventoryClickListener implements Listener {
    private Map<Player, Integer> player_int = BlockRestoreData.INSTANCE.getPlayer_int();
    private Map<Player, Boolean> player_chat = BlockRestoreData.INSTANCE.getPlayer_chat();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        /*主菜单*/
        if (e.getWhoClicked().getOpenInventory().getTitle().equalsIgnoreCase("§bBlockRestore主菜单")) {
            if (e.getRawSlot() == 13) {
                player_int.put((Player) e.getWhoClicked(), 1);
                player_chat.put((Player) e.getWhoClicked(), true);
                e.getWhoClicked().sendMessage("§7[§eBlockRestore§7]§b请输入添加物品的命名空间");
                e.getWhoClicked().closeInventory();
            }
            if (e.getRawSlot() == 14) {
                player.closeInventory();
                Bukkit.getScheduler().runTaskLater(BlockRestore.instance, () -> {
                    new editor().openInventory(player);
                }, 1L);
            }
            e.setCancelled(true);
        }

        /*setup菜单*/
        if (e.getWhoClicked().getOpenInventory().getTitle().equalsIgnoreCase("§b方块设置")) {
            changeBlock(e);
            if (e.getRawSlot() == 11) {
                player_int.put((Player) e.getWhoClicked(), 2);
                player_chat.put((Player) e.getWhoClicked(), true);
                e.getWhoClicked().sendMessage("§7[§eBlockRestore§7]§b请输入替换物品的命名空间");
                e.getWhoClicked().closeInventory();
            } else if (e.getRawSlot() == 15) {
                player_int.put((Player) e.getWhoClicked(), 3);
                player_chat.put((Player) e.getWhoClicked(), true);
                e.getWhoClicked().sendMessage("§7[§eBlockRestore§7]§b请输入方块恢复的时间");
                e.getWhoClicked().closeInventory();
            } else if (e.getRawSlot() == 16) {
                int time = BlockRestoreData.INSTANCE.getTime();
                if (time == -1) {
                    player.sendMessage("§7[§eBlockRestore§7]§c尚未定义时间!");
                    e.setCancelled(true);
                    return;
                }
                output(e, player, time);
                BlockRestoreData.INSTANCE.settime(-1);
                player.sendMessage("§7[§eBlockRestore§7]§2方块设置成功");
            }
            e.setCancelled(true);
        }

        /*editor*/
        if (e.getWhoClicked().getOpenInventory().getTitle().equalsIgnoreCase("§e方块总览")) {
            if (e.getCurrentItem() == null) return;
            if (e.getCurrentItem().getType() != Material.REDSTONE_BLOCK) {
                Bukkit.getScheduler().runTaskLater(BlockRestore.instance, () -> {
                    modify modify = new modify();
                    modify.clean();
                    ;
                    modify.openinv(player, e.getCurrentItem().getType().name());
                }, 1L);
            }
            e.setCancelled(true);
        }

        /*modify*/
        if (e.getWhoClicked().getOpenInventory().getTitle().equalsIgnoreCase("§a编辑器")) {
            /*player.sendMessage("打开了编辑器");*/
            if (e.getRawSlot() == 11) {
                player_int.put((Player) e.getWhoClicked(), 22);
                player_chat.put((Player) e.getWhoClicked(), true);
                e.getWhoClicked().sendMessage("§7[§eBlockRestore§7]§b请输入替换物品的命名空间");
                e.getWhoClicked().closeInventory();
            } else if (e.getRawSlot() == 15) {
                player_int.put((Player) e.getWhoClicked(), 33);
                player_chat.put((Player) e.getWhoClicked(), true);
                e.getWhoClicked().sendMessage("§7[§eBlockRestore§7]§b请输入方块恢复的时间");
                e.getWhoClicked().closeInventory();
            } else if (e.getRawSlot() == 16) {
                int time = BlockRestoreData.INSTANCE.getTime();
                output(e, player, time);
                player.sendMessage("§7[§eBlockRestore§7]§2方块修改成功");
            }
            changeBlock(e);
            e.setCancelled(true);
        }
    }

    private ItemStack getItem(InventoryClickEvent e, int slot) {
        return e.getWhoClicked().getOpenInventory().getItem(slot);
    }

    private Material getItemMaterial(InventoryClickEvent e, int slot) {
        return e.getWhoClicked().getOpenInventory().getItem(slot).getType();
    }

    private void setItem(InventoryClickEvent e, int slot, ItemStack itemStack) {
        e.getWhoClicked().getOpenInventory().setItem(slot, itemStack);
    }

    private void changeBlock(InventoryClickEvent e) {
        if (e.getRawSlot() == 10) {
            if (getItem(e, 10).equals(blocks.RED_WOOL.getItemStack())) {
                setItem(e, 10, blocks.LIME_WOOL.getItemStack());
            } else {
                setItem(e, 10, blocks.RED_WOOL.getItemStack());
            }
        } else if (e.getRawSlot() == 12) {
            if (getItem(e, 12).equals(blocks.GRASS_BLOCK.getItemStack())) {
                setItem(e, 12, blocks.BARRIER.getItemStack());
            } else {
                setItem(e, 12, blocks.GRASS_BLOCK.getItemStack());
            }
        } else if (e.getRawSlot() == 13) {
            if (getItem(e, 13).equals(blocks.ENCHANTING_TABLE.getItemStack())) {
                setItem(e, 13, blocks.CRAFTING_TABLE.getItemStack());
            } else {
                setItem(e, 13, blocks.ENCHANTING_TABLE.getItemStack());
            }
        } else if (e.getRawSlot() == 14) {
            if (getItem(e, 14).equals(blocks.CHEST.getItemStack())) {
                setItem(e, 14, blocks.ENDER_CHEST.getItemStack());
            } else {
                setItem(e, 14, blocks.CHEST.getItemStack());
            }
        }
    }

    private void output(InventoryClickEvent e, Player player, int time) {
        String blockname = getItemMaterial(e, 4).name();
        if (getItemMaterial(e, 10) == Material.REDSTONE_BLOCK) {
            BlockRestore.instance.getConfig().set("blocks." + blockname + ".replace", false);
        } else {
            BlockRestore.instance.getConfig().set("blocks." + blockname + ".replace", true);
        }
        BlockRestore.instance.getConfig().set("blocks." + blockname + ".replaceblock", getItemMaterial(e, 11).name());
        if (getItemMaterial(e, 12) == Material.GRASS) {
            BlockRestore.instance.getConfig().set("blocks." + blockname + ".denyplace", false);
        } else {
            BlockRestore.instance.getConfig().set("blocks." + blockname + ".denyplace", true);
        }
        if (getItemMaterial(e, 13) == Material.ENCHANTING_TABLE) {
            BlockRestore.instance.getConfig().set("blocks." + blockname + ".restore", true);
        } else {
            BlockRestore.instance.getConfig().set("blocks." + blockname + ".redstore", false);
        }
        if (getItemMaterial(e, 14) == Material.CHEST) {
            BlockRestore.instance.getConfig().set("blocks." + blockname + ".directgiveitem", true);
        } else {
            BlockRestore.instance.getConfig().set("blocks." + blockname + ".directgiveitem", false);
        }
        BlockRestore.instance.getConfig().set("blocks." + blockname + ".restoretime", time);
        BlockRestore.instance.saveConfig();
        player.closeInventory();
        BlockRestore.instance.initialize();
    }
}
