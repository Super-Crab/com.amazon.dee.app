package com.google.android.play.core.assetpacks;

import androidx.annotation.Nullable;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
abstract class zzet {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zza();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract long zzb();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract String zzc();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zzd();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zze();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract byte[] zzf();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzg() {
        if (zzc() == null) {
            return false;
        }
        return zzc().endsWith("/");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzh() {
        return zza() == 0;
    }
}
