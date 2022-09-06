package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.Intent;
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzv implements zzf {
    final /* synthetic */ SplitInstallSessionState zza;
    final /* synthetic */ Intent zzb;
    final /* synthetic */ Context zzc;
    final /* synthetic */ zzx zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzv(zzx zzxVar, SplitInstallSessionState splitInstallSessionState, Intent intent, Context context) {
        this.zzd = zzxVar;
        this.zza = splitInstallSessionState;
        this.zzb = intent;
        this.zzc = context;
    }

    @Override // com.google.android.play.core.splitinstall.zzf
    public final void zza() {
        r0.zzd.post(new zzw(this.zzd, this.zza, 5, 0));
    }

    @Override // com.google.android.play.core.splitinstall.zzf
    public final void zzb(@SplitInstallErrorCode int i) {
        r0.zzd.post(new zzw(this.zzd, this.zza, 6, i));
    }

    @Override // com.google.android.play.core.splitinstall.zzf
    public final void zzc() {
        com.google.android.play.core.internal.zzag zzagVar;
        if (!this.zzb.getBooleanExtra("triggered_from_app_after_verification", false)) {
            this.zzb.putExtra("triggered_from_app_after_verification", true);
            this.zzc.sendBroadcast(this.zzb);
            return;
        }
        zzagVar = ((com.google.android.play.core.listener.zzc) this.zzd).zza;
        zzagVar.zzb("Splits copied and verified more than once.", new Object[0]);
    }
}
