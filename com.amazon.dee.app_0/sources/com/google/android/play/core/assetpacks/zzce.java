package com.google.android.play.core.assetpacks;

import com.google.android.play.core.assetpacks.model.AssetPackStatus;
import java.io.InputStream;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzce extends zzdg {
    final int zza;
    final long zzb;
    final String zzc;
    final String zzd;
    final int zze;
    final int zzf;
    final int zzg;
    final long zzh;
    @AssetPackStatus
    final int zzi;
    final InputStream zzj;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzce(int i, String str, int i2, long j, String str2, String str3, int i3, int i4, int i5, long j2, @AssetPackStatus int i6, InputStream inputStream) {
        super(i, str);
        this.zza = i2;
        this.zzb = j;
        this.zzc = str2;
        this.zzd = str3;
        this.zze = i3;
        this.zzf = i4;
        this.zzg = i5;
        this.zzh = j2;
        this.zzi = i6;
        this.zzj = inputStream;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza() {
        return this.zzf + 1 == this.zzg;
    }
}
