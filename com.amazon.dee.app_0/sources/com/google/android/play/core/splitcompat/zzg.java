package com.google.android.play.core.splitcompat;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.ZipFile;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzg implements zzj {
    final /* synthetic */ zzs zza;
    final /* synthetic */ Set zzb;
    final /* synthetic */ AtomicBoolean zzc;
    final /* synthetic */ zzm zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzg(zzm zzmVar, zzs zzsVar, Set set, AtomicBoolean atomicBoolean) {
        this.zzd = zzmVar;
        this.zza = zzsVar;
        this.zzb = set;
        this.zzc = atomicBoolean;
    }

    @Override // com.google.android.play.core.splitcompat.zzj
    public final void zza(ZipFile zipFile, Set set) throws IOException {
        this.zzd.zzf(this.zza, set, new zzf(this));
    }
}
