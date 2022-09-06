package com.google.android.gms.internal.vision;

import java.util.Arrays;
/* loaded from: classes2.dex */
final class zzbq implements zzbs {
    private zzbq() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzbq(zzbp zzbpVar) {
        this();
    }

    @Override // com.google.android.gms.internal.vision.zzbs
    public final byte[] zzc(byte[] bArr, int i, int i2) {
        return Arrays.copyOfRange(bArr, i, i2 + i);
    }
}
