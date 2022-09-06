package com.amazon.deecomms.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes12.dex */
public final class NamedThreadFactory implements ThreadFactory {
    private final String mName;
    private final AtomicInteger mThreadNum = new AtomicInteger(1);

    /* JADX INFO: Access modifiers changed from: package-private */
    public NamedThreadFactory(String str) {
        this.mName = str;
    }

    public String getName() {
        return this.mName;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, String.format("%s#%d", this.mName, Integer.valueOf(this.mThreadNum.getAndIncrement())));
    }
}
