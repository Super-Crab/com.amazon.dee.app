package com.google.android.gms.internal.vision;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzbr extends zzbv {
    private final int zzgx;
    private final int zzgy;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbr(byte[] bArr, int i, int i2) {
        super(bArr);
        zzbo.zzb(i, i + i2, bArr.length);
        this.zzgx = i;
        this.zzgy = i2;
    }

    @Override // com.google.android.gms.internal.vision.zzbv, com.google.android.gms.internal.vision.zzbo
    public final int size() {
        return this.zzgy;
    }

    @Override // com.google.android.gms.internal.vision.zzbv
    protected final int zzav() {
        return this.zzgx;
    }

    @Override // com.google.android.gms.internal.vision.zzbv, com.google.android.gms.internal.vision.zzbo
    public final byte zzl(int i) {
        int size = size();
        if (((size - (i + 1)) | i) < 0) {
            if (i >= 0) {
                throw new ArrayIndexOutOfBoundsException(GeneratedOutlineSupport1.outline28(40, "Index > length: ", i, ", ", size));
            }
            throw new ArrayIndexOutOfBoundsException(GeneratedOutlineSupport1.outline27(22, "Index < 0: ", i));
        }
        return this.zzha[this.zzgx + i];
    }
}
