package com.amazon.device.utils.thirdparty;

import java.util.concurrent.ThreadFactory;
/* loaded from: classes12.dex */
public class BackgroundThreadFactory implements ThreadFactory {
    private final String mThreadName;

    public BackgroundThreadFactory(String str) {
        this.mThreadName = str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setPriority(4);
        thread.setName(this.mThreadName);
        return thread;
    }
}
