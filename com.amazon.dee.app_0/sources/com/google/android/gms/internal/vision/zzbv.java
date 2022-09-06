package com.google.android.gms.internal.vision;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.nio.charset.Charset;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class zzbv extends zzbu {
    protected final byte[] zzha;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbv(byte[] bArr) {
        this.zzha = bArr;
    }

    @Override // com.google.android.gms.internal.vision.zzbo
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbo) || size() != ((zzbo) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzbv)) {
            return obj.equals(this);
        }
        zzbv zzbvVar = (zzbv) obj;
        int zzau = zzau();
        int zzau2 = zzbvVar.zzau();
        if (zzau != 0 && zzau2 != 0 && zzau != zzau2) {
            return false;
        }
        return zza(zzbvVar, 0, size());
    }

    @Override // com.google.android.gms.internal.vision.zzbo
    public int size() {
        return this.zzha.length;
    }

    @Override // com.google.android.gms.internal.vision.zzbo
    protected final int zza(int i, int i2, int i3) {
        return zzct.zza(i, this.zzha, zzav(), i3);
    }

    @Override // com.google.android.gms.internal.vision.zzbo
    protected final String zza(Charset charset) {
        return new String(this.zzha, zzav(), size(), charset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzbo
    public final void zza(zzbn zzbnVar) throws IOException {
        zzbnVar.zza(this.zzha, zzav(), size());
    }

    @Override // com.google.android.gms.internal.vision.zzbu
    final boolean zza(zzbo zzboVar, int i, int i2) {
        if (i2 > zzboVar.size()) {
            int size = size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        } else if (i2 > zzboVar.size()) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline28(59, "Ran off end of other: 0, ", i2, ", ", zzboVar.size()));
        } else {
            if (!(zzboVar instanceof zzbv)) {
                return zzboVar.zzc(0, i2).equals(zzc(0, i2));
            }
            zzbv zzbvVar = (zzbv) zzboVar;
            byte[] bArr = this.zzha;
            byte[] bArr2 = zzbvVar.zzha;
            int zzav = zzav() + i2;
            int zzav2 = zzav();
            int zzav3 = zzbvVar.zzav();
            while (zzav2 < zzav) {
                if (bArr[zzav2] != bArr2[zzav3]) {
                    return false;
                }
                zzav2++;
                zzav3++;
            }
            return true;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzbo
    public final boolean zzat() {
        int zzav = zzav();
        return zzfn.zze(this.zzha, zzav, size() + zzav);
    }

    protected int zzav() {
        return 0;
    }

    @Override // com.google.android.gms.internal.vision.zzbo
    public final zzbo zzc(int i, int i2) {
        int zzb = zzbo.zzb(0, i2, size());
        return zzb == 0 ? zzbo.zzgt : new zzbr(this.zzha, zzav(), zzb);
    }

    @Override // com.google.android.gms.internal.vision.zzbo
    public byte zzl(int i) {
        return this.zzha[i];
    }
}
