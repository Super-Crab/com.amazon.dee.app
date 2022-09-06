package com.google.firebase.messaging;

import java.util.concurrent.Executor;
/* compiled from: com.google.firebase:firebase-messaging@@20.1.0 */
/* loaded from: classes3.dex */
final /* synthetic */ class zzh implements Executor {
    static final Executor zza = new zzh();

    private zzh() {
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
