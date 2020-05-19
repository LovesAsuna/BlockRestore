package org.sct.BlockRestore.updater;

import org.sct.BlockRestore.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class update implements Runnable{

    @Override
    public void run() {
        try {
            URL url = new URL("https://api.github.com/repos/LovesAsuna/BlockRestore/releases");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String newestversion = reader.readLine().split(",") [7];
            String currentVersion = Main.getInstance().getDescription().getVersion();
            String[] replace = {"tag_name","\"",":"};
            for (String cr :replace) {
                newestversion = newestversion.replace(cr,"");
            }
            reader.close();
            if (currentVersion.equalsIgnoreCase(newestversion)) {
                Main.getInstance().getServer().getConsoleSender().sendMessage("§7[§eBlockRestore§7]§2你正在使用最新的" + currentVersion + "版本");
            } else {
                Main.getInstance().getServer().getConsoleSender().sendMessage("§7[§eBlockRestore§7]§c最新版本为" + newestversion);
                Main.getInstance().getServer().getConsoleSender().sendMessage("§7[§eBlockRestore§7]§c请前往https://www.mcbbs.net/thread-916058-1-1.html下载");
            }
        } catch (IOException e) {
            Main.getInstance().getLogger().severe("插件在检测版本时发生错误!");
            e.printStackTrace();
        }
    }
}