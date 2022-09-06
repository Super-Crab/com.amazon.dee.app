package com.google.android.play.core.splitinstall;

import com.google.android.play.core.internal.zzcq;
import com.google.android.play.core.internal.zzcs;
import java.io.File;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zze implements zzp {
    private final zze zza = this;
    private final zzcs zzb;
    private final zzcs zzc;
    private final zzcs zzd;
    private final zzcs zze;
    private final zzcs zzf;
    private final zzcs zzg;
    private final zzcs zzh;
    private final zzcs zzi;
    private final zzcs zzj;
    private final zzcs zzk;
    private final zzcs zzl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zze(zzac zzacVar, zzd zzdVar) {
        this.zzb = new zzad(zzacVar);
        this.zzc = zzcq.zzc(new zzbd(this.zzb));
        this.zzd = zzcq.zzc(new zzag(zzacVar));
        this.zze = zzcq.zzc(new zzt(this.zzb));
        this.zzf = zzcq.zzc(new zzbf(this.zzb));
        this.zzg = zzcq.zzc(new zzab(this.zzc, this.zzd, this.zze, this.zzf));
        this.zzh = zzcq.zzc(new zzaf(this.zzb));
        zzcs zzcsVar = this.zzh;
        this.zzi = new zzae(zzcsVar);
        this.zzj = zzcq.zzc(new com.google.android.play.core.splitinstall.testing.zzr(this.zzb, zzcsVar, this.zze, this.zzi));
        this.zzk = zzcq.zzc(new zzm(this.zzg, this.zzj, this.zzh));
        this.zzl = zzcq.zzc(new zzah(zzacVar, this.zzk));
    }

    @Override // com.google.android.play.core.splitinstall.zzp
    public final SplitInstallManager zza() {
        return (SplitInstallManager) this.zzl.zza();
    }

    @Override // com.google.android.play.core.splitinstall.zzp
    public final File zzb() {
        return (File) this.zzh.zza();
    }
}
