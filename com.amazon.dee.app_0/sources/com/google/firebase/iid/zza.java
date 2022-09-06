package com.google.firebase.iid;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* loaded from: classes3.dex */
final class zza {
    private static final Executor zza = zzc.zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Executor zza() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Executor zzb() {
        return new ThreadPoolExecutor(0, 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(), zzd.zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final /* synthetic */ Thread zza(Runnable runnable) {
        return new Thread(runnable, "firebase-iid-executor");
    }
}
