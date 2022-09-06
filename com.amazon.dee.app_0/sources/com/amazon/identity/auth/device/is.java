package com.amazon.identity.auth.device;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class is implements ThreadFactory {
    private final String mName;
    private final AtomicInteger rt = new AtomicInteger(1);

    /* JADX INFO: Access modifiers changed from: package-private */
    public is(String str) {
        this.mName = str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, String.format("%s#%d", this.mName, Integer.valueOf(this.rt.getAndIncrement())));
    }
}
