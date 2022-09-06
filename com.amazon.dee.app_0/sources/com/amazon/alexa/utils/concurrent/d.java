package com.amazon.alexa.utils.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes10.dex */
class d implements ThreadFactory {
    private final String a;
    private final AtomicInteger b = new AtomicInteger(0);

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(String str) {
        this.a = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String a() {
        return this.a;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.a + ProcessIdUtil.DEFAULT_PROCESSID + this.b.getAndIncrement());
        thread.setDaemon(true);
        return thread;
    }
}
