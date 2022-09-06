package com.google.android.gms.internal.vision;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzbz extends zzbx {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzhf;
    private int zzhg;
    private int zzhh;
    private int zzhi;

    private zzbz(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzhi = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzhh = this.pos;
        this.zzhf = z;
    }

    @Override // com.google.android.gms.internal.vision.zzbx
    public final int zzay() {
        return this.pos - this.zzhh;
    }

    @Override // com.google.android.gms.internal.vision.zzbx
    public final int zzn(int i) throws zzcx {
        if (i >= 0) {
            int zzay = i + zzay();
            int i2 = this.zzhi;
            if (zzay > i2) {
                throw zzcx.zzcb();
            }
            this.zzhi = zzay;
            this.limit += this.zzhg;
            int i3 = this.limit;
            int i4 = i3 - this.zzhh;
            int i5 = this.zzhi;
            if (i4 > i5) {
                this.zzhg = i4 - i5;
                this.limit = i3 - this.zzhg;
            } else {
                this.zzhg = 0;
            }
            return i2;
        }
        throw zzcx.zzcc();
    }
}
