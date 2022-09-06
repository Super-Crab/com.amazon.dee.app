package com.google.android.play.core.assetpacks;

import androidx.annotation.Nullable;
import java.util.Arrays;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzds {
    private byte[] zza = new byte[4096];
    private int zzb;
    private long zzc;
    private long zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private boolean zzh;
    @Nullable
    private String zzi;

    public zzds() {
        zzd();
    }

    private final int zze(int i, byte[] bArr, int i2, int i3) {
        int i4 = this.zzb;
        if (i4 < i) {
            int min = Math.min(i3, i - i4);
            System.arraycopy(bArr, i2, this.zza, this.zzb, min);
            int i5 = this.zzb + min;
            this.zzb = i5;
            if (i5 >= i) {
                return min;
            }
            return -1;
        }
        return 0;
    }

    public final int zza() {
        return this.zzf;
    }

    public final int zzb(byte[] bArr, int i, int i2) {
        int zze = zze(30, bArr, i, i2);
        if (zze != -1) {
            if (this.zzc == -1) {
                long zzc = zzbr.zzc(this.zza, 0);
                this.zzc = zzc;
                if (zzc == 67324752) {
                    this.zzh = false;
                    this.zzd = zzbr.zzc(this.zza, 18);
                    this.zzg = zzbr.zza(this.zza, 8);
                    this.zze = zzbr.zza(this.zza, 26);
                    int zza = this.zze + 30 + zzbr.zza(this.zza, 28);
                    this.zzf = zza;
                    int length = this.zza.length;
                    if (length < zza) {
                        do {
                            length += length;
                        } while (length < zza);
                        this.zza = Arrays.copyOf(this.zza, length);
                    }
                } else {
                    this.zzh = true;
                }
            }
            int zze2 = zze(this.zzf, bArr, i + zze, i2 - zze);
            if (zze2 == -1) {
                return -1;
            }
            int i3 = zze + zze2;
            if (!this.zzh && this.zzi == null) {
                this.zzi = new String(this.zza, 30, this.zze);
            }
            return i3;
        }
        return -1;
    }

    public final zzet zzc() {
        int i = this.zzb;
        int i2 = this.zzf;
        if (i < i2) {
            return new zzbq(this.zzi, this.zzd, this.zzg, true, this.zzh, Arrays.copyOf(this.zza, i));
        }
        zzbq zzbqVar = new zzbq(this.zzi, this.zzd, this.zzg, false, this.zzh, Arrays.copyOf(this.zza, i2));
        zzd();
        return zzbqVar;
    }

    public final void zzd() {
        this.zzb = 0;
        this.zze = -1;
        this.zzc = -1L;
        this.zzh = false;
        this.zzf = 30;
        this.zzd = -1L;
        this.zzg = -1;
        this.zzi = null;
    }
}
