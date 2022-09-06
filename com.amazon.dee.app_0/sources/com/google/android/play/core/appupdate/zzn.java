package com.google.android.play.core.appupdate;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.zzag;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
class zzn extends com.google.android.play.core.internal.zzq {
    final zzag zza;
    final com.google.android.play.core.tasks.zzi zzb;
    final /* synthetic */ zzq zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzn(zzq zzqVar, zzag zzagVar, com.google.android.play.core.tasks.zzi zziVar) {
        this.zzc = zzqVar;
        this.zza = zzagVar;
        this.zzb = zziVar;
    }

    @Override // com.google.android.play.core.internal.zzr
    public void zzb(Bundle bundle) throws RemoteException {
        this.zzc.zza.zzs(this.zzb);
        this.zza.zzd("onCompleteUpdate", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.zzr
    public void zzc(Bundle bundle) throws RemoteException {
        this.zzc.zza.zzs(this.zzb);
        this.zza.zzd("onRequestInfo", new Object[0]);
    }
}
