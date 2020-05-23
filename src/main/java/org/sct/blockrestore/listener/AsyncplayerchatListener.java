package org.sct.blockrestore.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.sct.blockrestore.BlockRestore;
import org.sct.blockrestore.data.BlockRestoreData;
import org.sct.blockrestore.gui.modify;
import org.sct.blockrestore.gui.setup;

import java.util.Map;


public class AsyncplayerchatListener implements Listener {
    private Map<Player, Boolean> player_chat = BlockRestoreData.INSTANCE.getPlayer_chat();
    private Map<Player, Integer> player_int = BlockRestoreData.INSTANCE.getPlayer_int();
    private Material inputmr = BlockRestoreData.INSTANCE.getInputmr();
    private setup setup = new setup();
    private modify modify = new modify();

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (player_chat.get(player) != null && player_chat.get(player)) {//启动了聊天抑制
            if (e.getMessage().equalsIgnoreCase("cancel")) {
                player_chat.remove(player);
                player.sendMessage("§2操作取消成功!");
                e.setCancelled(true);
                return;
            } else {
                if (player_int.get(player) == 3) {
                    Bukkit.getScheduler().runTaskLater(BlockRestore.instance, () -> {
                        try {//判断输入是否有效
                            BlockRestoreData.INSTANCE.settime(Integer.parseInt(e.getMessage()));
                            setup.openinv_modifytime(player);
                        } catch (Exception ex) {
                            player.sendMessage("§c你输入的时间有误,请重新输入!");
                        }
                    }, 0L);
                }

                if (player_int.get(player) == 33) {
                    Bukkit.getScheduler().runTaskLater(BlockRestore.instance, () -> {
                        try {//判断输入是否有效
                            BlockRestoreData.INSTANCE.settime(Integer.parseInt(e.getMessage()));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            player.sendMessage("§c你输入的时间有误,请重新输入!");
                        }
                        modify.openinv_modifytime(player);
                    }, 0L);
                }

                if (player_int.get(player) == 1 || player_int.get(player) == 2) {
                    inputmr = Material.getMaterial(e.getMessage());
                    if (inputmr == null) {//判断输入是否有效
                        player.sendMessage("§c你输入的命名空间有误,请重新输入!");
                    } else {
                        player_chat.remove(player);
                        Bukkit.getScheduler().runTaskLater(BlockRestore.instance, () -> {
                            if (player_int.get(player) == 1) {
                                setup.clean();
                                setup.openinv(player, e.getMessage());
                                player_int.remove(player);
                            } else if (player_int.get(player) == 2) {
                                setup.openinv_modifyblock(player, e.getMessage());
                            }
                        }, 0L);
                    }
                }

                if (player_int.get(player) == 11 || player_int.get(player) == 22) {
                    inputmr = Material.getMaterial(e.getMessage());
                    if (inputmr == null) {//判断输入是否有效
                        player.sendMessage("§c你输入的命名空间有误,请重新输入!");
                    } else {
                        player_chat.remove(player);
                        Bukkit.getScheduler().runTaskLater(BlockRestore.instance, () -> {
                            if (player_int.get(player) == 11) {
                                modify.clean();
                                modify.openinv(player, e.getMessage());
                                player_int.remove(player);
                            } else if (player_int.get(player) == 22) {
                                modify.openinv_modifyblock(player, e.getMessage());
                            }
                        }, 0L);
                    }
                }
                e.setCancelled(true);
            }
        }
    }
}
