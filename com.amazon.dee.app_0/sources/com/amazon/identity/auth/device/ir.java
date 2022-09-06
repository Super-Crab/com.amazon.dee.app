package com.amazon.identity.auth.device;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ir implements Executor {
    private final Executor rs;

    public ir(String str) {
        this.rs = new el(Executors.newCachedThreadPool(new is(str)));
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.rs.execute(runnable);
    }
}
