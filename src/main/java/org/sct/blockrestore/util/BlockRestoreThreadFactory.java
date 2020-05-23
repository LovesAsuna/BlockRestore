package org.sct.blockrestore.util;

import java.util.concurrent.ThreadFactory;

/**
 * @author LovesAsuna
 * @date 2020/5/23 21:20
 */

public class BlockRestoreThreadFactory implements ThreadFactory {
    private final String threadName;

    public BlockRestoreThreadFactory(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(threadName);
        return thread;
    }
}