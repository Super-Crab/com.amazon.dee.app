package com.amazon.identity.auth.device;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class el implements Executor {
    private final ExecutorService gY;

    public el(ExecutorService executorService) {
        this.gY = executorService;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (jk.gR()) {
            runnable.run();
        } else {
            this.gY.execute(runnable);
        }
    }
}
