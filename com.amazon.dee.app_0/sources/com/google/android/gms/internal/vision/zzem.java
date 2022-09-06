package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzcr;
/* loaded from: classes2.dex */
final class zzem implements zzdv {
    private final int flags;
    private final String info;
    private final Object[] zznf;
    private final zzdx zzni;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzem(zzdx zzdxVar, String str, Object[] objArr) {
        this.zzni = zzdxVar;
        this.info = str;
        this.zznf = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.flags = charAt;
            return;
        }
        int i = charAt & 8191;
        int i2 = 13;
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char charAt2 = str.charAt(i3);
            if (charAt2 < 55296) {
                this.flags = i | (charAt2 << i2);
                return;
            }
            i |= (charAt2 & 8191) << i2;
            i2 += 13;
            i3 = i4;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzdv
    public final int zzcv() {
        return (this.flags & 1) == 1 ? zzcr.zzd.zzlg : zzcr.zzd.zzlh;
    }

    @Override // com.google.android.gms.internal.vision.zzdv
    public final boolean zzcw() {
        return (this.flags & 2) == 2;
    }

    @Override // com.google.android.gms.internal.vision.zzdv
    public final zzdx zzcx() {
        return this.zzni;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzde() {
        return this.info;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Object[] zzdf() {
        return this.zznf;
    }
}
