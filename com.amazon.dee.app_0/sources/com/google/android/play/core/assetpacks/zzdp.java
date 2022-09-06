package com.google.android.play.core.assetpacks;

import android.content.Context;
import java.io.File;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzdp implements com.google.android.play.core.internal.zzcs {
    private final com.google.android.play.core.internal.zzcs zza;
    private final com.google.android.play.core.internal.zzcs zzb;
    private final com.google.android.play.core.internal.zzcs zzc;
    private final com.google.android.play.core.internal.zzcs zzd;
    private final com.google.android.play.core.internal.zzcs zze;
    private final com.google.android.play.core.internal.zzcs zzf;
    private final com.google.android.play.core.internal.zzcs zzg;

    public zzdp(com.google.android.play.core.internal.zzcs zzcsVar, com.google.android.play.core.internal.zzcs zzcsVar2, com.google.android.play.core.internal.zzcs zzcsVar3, com.google.android.play.core.internal.zzcs zzcsVar4, com.google.android.play.core.internal.zzcs zzcsVar5, com.google.android.play.core.internal.zzcs zzcsVar6, com.google.android.play.core.internal.zzcs zzcsVar7) {
        this.zza = zzcsVar;
        this.zzb = zzcsVar2;
        this.zzc = zzcsVar3;
        this.zzd = zzcsVar4;
        this.zze = zzcsVar5;
        this.zzf = zzcsVar6;
        this.zzg = zzcsVar7;
    }

    @Override // com.google.android.play.core.internal.zzcs
    public final /* bridge */ /* synthetic */ Object zza() {
        File externalFilesDir;
        String str = (String) this.zza.zza();
        Object zza = this.zzb.zza();
        Object zza2 = this.zzc.zza();
        Context zzb = ((zzu) this.zzd).zzb();
        Object zza3 = this.zze.zza();
        com.google.android.play.core.internal.zzco zzb2 = com.google.android.play.core.internal.zzcq.zzb(this.zzf);
        zzbb zzbbVar = (zzbb) zza;
        zzco zzcoVar = (zzco) zza2;
        zzed zzedVar = (zzed) zza3;
        zzeb zzebVar = (zzeb) this.zzg.zza();
        if (str != null) {
            externalFilesDir = new File(zzb.getExternalFilesDir(null), str);
        } else {
            externalFilesDir = zzb.getExternalFilesDir(null);
        }
        return new zzdo(externalFilesDir, zzbbVar, zzcoVar, zzb, zzedVar, zzb2, zzebVar);
    }
}
