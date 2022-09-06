package com.google.android.play.core.assetpacks;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.bouncycastle.asn1.cmc.BodyPartID;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzbw extends FilterInputStream {
    private final zzds zza;
    private byte[] zzb;
    private long zzc;
    private boolean zzd;
    private boolean zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbw(InputStream inputStream) {
        super(inputStream);
        this.zza = new zzds();
        this.zzb = new byte[4096];
        this.zzd = false;
        this.zze = false;
    }

    private final int zze(byte[] bArr, int i, int i2) throws IOException {
        return Math.max(0, super.read(bArr, i, i2));
    }

    private final boolean zzf(int i) throws IOException {
        int zze = zze(this.zzb, 0, i);
        if (zze != i) {
            int i2 = i - zze;
            if (zze(this.zzb, zze, i2) != i2) {
                this.zza.zzb(this.zzb, 0, zze);
                return false;
            }
        }
        this.zza.zzb(this.zzb, 0, i);
        return true;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long zza() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzet zzb() throws IOException {
        byte[] bArr;
        if (this.zzc <= 0) {
            if (this.zzd) {
            }
            return new zzbq(null, -1L, -1, false, false, null);
        }
        do {
            bArr = this.zzb;
        } while (read(bArr, 0, bArr.length) != -1);
        if (this.zzd && !this.zze) {
            if (!zzf(30)) {
                this.zzd = true;
                return this.zza.zzc();
            }
            zzet zzc = this.zza.zzc();
            if (zzc.zzd()) {
                this.zze = true;
                return zzc;
            } else if (zzc.zzb() != BodyPartID.bodyIdMax) {
                int zza = this.zza.zza() - 30;
                long j = zza;
                int length = this.zzb.length;
                if (j > length) {
                    do {
                        length += length;
                    } while (length < j);
                    this.zzb = Arrays.copyOf(this.zzb, length);
                }
                if (!zzf(zza)) {
                    this.zzd = true;
                    return this.zza.zzc();
                }
                zzet zzc2 = this.zza.zzc();
                this.zzc = zzc2.zzb();
                return zzc2;
            } else {
                throw new zzck("Files bigger than 4GiB are not supported.");
            }
        }
        return new zzbq(null, -1L, -1, false, false, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzc() {
        return this.zze;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzd() {
        return this.zzd;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        long j = this.zzc;
        if (j <= 0 || this.zzd) {
            return -1;
        }
        int zze = zze(bArr, i, (int) Math.min(j, i2));
        this.zzc -= zze;
        if (zze != 0) {
            return zze;
        }
        this.zzd = true;
        return 0;
    }
}
