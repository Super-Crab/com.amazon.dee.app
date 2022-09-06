package com.google.android.play.core.assetpacks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzx implements com.google.android.play.core.internal.zzcs {
    @Override // com.google.android.play.core.internal.zzcs
    public final /* synthetic */ Object zza() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor(zzo.zza);
        com.google.android.play.core.internal.zzcr.zza(newSingleThreadExecutor);
        return newSingleThreadExecutor;
    }
}
