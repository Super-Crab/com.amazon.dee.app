package com.google.android.play.core.assetpacks;

import java.util.concurrent.ThreadFactory;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final /* synthetic */ class zzn implements ThreadFactory {
    public static final /* synthetic */ zzn zza = new zzn();

    private /* synthetic */ zzn() {
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, "AssetPackBackgroundExecutor");
    }
}
