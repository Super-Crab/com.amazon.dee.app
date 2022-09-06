package com.google.android.gms.internal.vision;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzbt {
    private final byte[] buffer;
    private final zzca zzgz;

    private zzbt(int i) {
        this.buffer = new byte[i];
        this.zzgz = zzca.zzd(this.buffer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzbt(int i, zzbp zzbpVar) {
        this(i);
    }

    public final zzbo zzaw() {
        this.zzgz.zzba();
        return new zzbv(this.buffer);
    }

    public final zzca zzax() {
        return this.zzgz;
    }
}
