package com.google.android.play.core.appupdate;

import com.google.android.play.core.internal.zzcq;
import com.google.android.play.core.internal.zzcs;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzaa {
    private final zzaa zza = this;
    private final zzcs zzb;
    private final zzcs zzc;
    private final zzcs zzd;
    private final zzcs zze;
    private final zzcs zzf;
    private final zzcs zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzaa(zzh zzhVar, zzz zzzVar) {
        this.zzb = new zzj(zzhVar);
        this.zzc = zzcq.zzc(new zzt(this.zzb));
        this.zzd = zzcq.zzc(new zzr(this.zzb, this.zzc));
        this.zze = zzcq.zzc(new zzc(this.zzb));
        this.zzf = zzcq.zzc(new zzg(this.zzd, this.zze, this.zzb));
        this.zzg = zzcq.zzc(new zzi(this.zzf));
    }

    public final AppUpdateManager zza() {
        return (AppUpdateManager) this.zzg.zza();
    }
}
