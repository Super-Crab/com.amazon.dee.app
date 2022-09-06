package com.google.firebase.iid;

import java.util.concurrent.Executor;
/* compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* loaded from: classes3.dex */
final /* synthetic */ class zzc implements Executor {
    static final Executor zza = new zzc();

    private zzc() {
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
