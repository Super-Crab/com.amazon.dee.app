package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzcn extends OutputStream {
    private final zzds zza = new zzds();
    private final File zzb;
    private final zzen zzc;
    private long zzd;
    private long zze;
    private FileOutputStream zzf;
    private zzet zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcn(File file, zzen zzenVar) {
        this.zzb = file;
        this.zzc = zzenVar;
    }

    @Override // java.io.OutputStream
    public final void write(int i) throws IOException {
        write(new byte[]{(byte) i}, 0, 1);
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) throws IOException {
        int min;
        while (i2 > 0) {
            if (this.zzd == 0 && this.zze == 0) {
                int zzb = this.zza.zzb(bArr, i, i2);
                if (zzb == -1) {
                    return;
                }
                i += zzb;
                i2 -= zzb;
                this.zzg = this.zza.zzc();
                if (this.zzg.zzd()) {
                    this.zzd = 0L;
                    this.zzc.zzl(this.zzg.zzf(), 0, this.zzg.zzf().length);
                    this.zze = this.zzg.zzf().length;
                } else if (this.zzg.zzh() && !this.zzg.zzg()) {
                    this.zzc.zzj(this.zzg.zzf());
                    File file = new File(this.zzb, this.zzg.zzc());
                    file.getParentFile().mkdirs();
                    this.zzd = this.zzg.zzb();
                    this.zzf = new FileOutputStream(file);
                } else {
                    byte[] zzf = this.zzg.zzf();
                    this.zzc.zzl(zzf, 0, zzf.length);
                    this.zzd = this.zzg.zzb();
                }
            }
            if (!this.zzg.zzg()) {
                if (this.zzg.zzd()) {
                    this.zzc.zze(this.zze, bArr, i, i2);
                    this.zze += i2;
                    min = i2;
                } else if (this.zzg.zzh()) {
                    min = (int) Math.min(i2, this.zzd);
                    this.zzf.write(bArr, i, min);
                    long j = this.zzd - min;
                    this.zzd = j;
                    if (j == 0) {
                        this.zzf.close();
                    }
                } else {
                    min = (int) Math.min(i2, this.zzd);
                    this.zzc.zze((this.zzg.zzf().length + this.zzg.zzb()) - this.zzd, bArr, i, min);
                    this.zzd -= min;
                }
                i += min;
                i2 -= min;
            }
        }
    }
}
