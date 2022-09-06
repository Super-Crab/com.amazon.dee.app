package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.zzcb;
import java.util.List;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
class zzbb extends zzcb {
    final com.google.android.play.core.tasks.zzi zza;
    final /* synthetic */ zzbc zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbb(zzbc zzbcVar, com.google.android.play.core.tasks.zzi zziVar) {
        this.zzb = zzbcVar;
        this.zza = zziVar;
    }

    public void zzb(int i, Bundle bundle) throws RemoteException {
        com.google.android.play.core.internal.zzag zzagVar;
        this.zzb.zza.zzs(this.zza);
        zzagVar = zzbc.zzb;
        zzagVar.zzd("onCancelInstall(%d)", Integer.valueOf(i));
    }

    public void zzc(Bundle bundle) throws RemoteException {
        com.google.android.play.core.internal.zzag zzagVar;
        this.zzb.zza.zzs(this.zza);
        zzagVar = zzbc.zzb;
        zzagVar.zzd("onDeferredInstall", new Object[0]);
    }

    public void zzd(Bundle bundle) throws RemoteException {
        com.google.android.play.core.internal.zzag zzagVar;
        this.zzb.zza.zzs(this.zza);
        zzagVar = zzbc.zzb;
        zzagVar.zzd("onDeferredLanguageInstall", new Object[0]);
    }

    public void zze(Bundle bundle) throws RemoteException {
        com.google.android.play.core.internal.zzag zzagVar;
        this.zzb.zza.zzs(this.zza);
        zzagVar = zzbc.zzb;
        zzagVar.zzd("onDeferredLanguageUninstall", new Object[0]);
    }

    public void zzf(Bundle bundle) throws RemoteException {
        com.google.android.play.core.internal.zzag zzagVar;
        this.zzb.zza.zzs(this.zza);
        zzagVar = zzbc.zzb;
        zzagVar.zzd("onDeferredUninstall", new Object[0]);
    }

    public void zzg(int i, Bundle bundle) throws RemoteException {
        com.google.android.play.core.internal.zzag zzagVar;
        this.zzb.zza.zzs(this.zza);
        zzagVar = zzbc.zzb;
        zzagVar.zzd("onGetSession(%d)", Integer.valueOf(i));
    }

    public void zzh(List list) throws RemoteException {
        com.google.android.play.core.internal.zzag zzagVar;
        this.zzb.zza.zzs(this.zza);
        zzagVar = zzbc.zzb;
        zzagVar.zzd("onGetSessionStates", new Object[0]);
    }

    public void zzi(int i, Bundle bundle) throws RemoteException {
        com.google.android.play.core.internal.zzag zzagVar;
        this.zzb.zza.zzs(this.zza);
        zzagVar = zzbc.zzb;
        zzagVar.zzd("onStartInstall(%d)", Integer.valueOf(i));
    }

    @Override // com.google.android.play.core.internal.zzcc
    public final void zzj(int i, Bundle bundle) throws RemoteException {
        com.google.android.play.core.internal.zzag zzagVar;
        this.zzb.zza.zzs(this.zza);
        zzagVar = zzbc.zzb;
        zzagVar.zzd("onCompleteInstall(%d)", Integer.valueOf(i));
    }

    @Override // com.google.android.play.core.internal.zzcc
    public final void zzk(Bundle bundle) throws RemoteException {
        com.google.android.play.core.internal.zzag zzagVar;
        this.zzb.zza.zzs(this.zza);
        zzagVar = zzbc.zzb;
        zzagVar.zzd("onCompleteInstallForAppUpdate", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.zzcc
    public final void zzl(Bundle bundle) throws RemoteException {
        com.google.android.play.core.internal.zzag zzagVar;
        this.zzb.zza.zzs(this.zza);
        int i = bundle.getInt("error_code");
        zzagVar = zzbc.zzb;
        zzagVar.zzb("onError(%d)", Integer.valueOf(i));
        this.zza.zzd(new SplitInstallException(i));
    }

    @Override // com.google.android.play.core.internal.zzcc
    public final void zzm(Bundle bundle) throws RemoteException {
        com.google.android.play.core.internal.zzag zzagVar;
        this.zzb.zza.zzs(this.zza);
        zzagVar = zzbc.zzb;
        zzagVar.zzd("onGetSplitsForAppUpdate", new Object[0]);
    }
}
