package com.google.firebase.iid;

import java.util.concurrent.ThreadFactory;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* loaded from: classes3.dex */
public final /* synthetic */ class zzd implements ThreadFactory {
    static final ThreadFactory zza = new zzd();

    private zzd() {
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        return zza.zza(runnable);
    }
}
