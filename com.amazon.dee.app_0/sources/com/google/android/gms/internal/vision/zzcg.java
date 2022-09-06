package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzcl;
import java.io.IOException;
import java.util.Map;
/* loaded from: classes2.dex */
abstract class zzcg<T extends zzcl<T>> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zza(Map.Entry<?, ?> entry);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(zzfz zzfzVar, Map.Entry<?, ?> entry) throws IOException;

    abstract void zza(Object obj, zzcj<T> zzcjVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract zzcj<T> zzb(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract zzcj<T> zzc(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzd(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zze(zzdx zzdxVar);
}
