package com.dp.utils;

import android.os.Process;
/* loaded from: classes2.dex */
public class DpBackgroundThreadFactory extends DpThreadFactory {
    public DpBackgroundThreadFactory(String str) {
        super(str);
    }

    @Override // com.dp.utils.DpThreadFactory, java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        return new Thread(this.mThreadGroup, runnable, buildNextThreadName()) { // from class: com.dp.utils.DpBackgroundThreadFactory.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Process.setThreadPriority(10);
                super.run();
            }
        };
    }
}
