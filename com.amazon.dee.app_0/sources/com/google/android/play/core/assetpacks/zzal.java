package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
import java.util.List;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
class zzal extends com.google.android.play.core.internal.zzv {
    final com.google.android.play.core.tasks.zzi zza;
    final /* synthetic */ zzaw zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzal(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar) {
        this.zzb = zzawVar;
        this.zza = zziVar;
    }

    @Override // com.google.android.play.core.internal.zzw
    public final void zzb(int i, Bundle bundle) {
        com.google.android.play.core.internal.zzas zzasVar;
        com.google.android.play.core.internal.zzag zzagVar;
        zzasVar = this.zzb.zzf;
        zzasVar.zzs(this.zza);
        zzagVar = zzaw.zza;
        zzagVar.zzd("onCancelDownload(%d)", Integer.valueOf(i));
    }

    @Override // com.google.android.play.core.internal.zzw
    public final void zzc(Bundle bundle) {
        com.google.android.play.core.internal.zzas zzasVar;
        com.google.android.play.core.internal.zzag zzagVar;
        zzasVar = this.zzb.zzf;
        zzasVar.zzs(this.zza);
        zzagVar = zzaw.zza;
        zzagVar.zzd("onCancelDownloads()", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.zzw
    public void zzd(Bundle bundle) {
        com.google.android.play.core.internal.zzas zzasVar;
        com.google.android.play.core.internal.zzag zzagVar;
        zzasVar = this.zzb.zzf;
        zzasVar.zzs(this.zza);
        int i = bundle.getInt("error_code");
        zzagVar = zzaw.zza;
        zzagVar.zzb("onError(%d)", Integer.valueOf(i));
        this.zza.zzd(new AssetPackException(i));
    }

    @Override // com.google.android.play.core.internal.zzw
    public void zze(Bundle bundle, Bundle bundle2) throws RemoteException {
        com.google.android.play.core.internal.zzas zzasVar;
        com.google.android.play.core.internal.zzag zzagVar;
        zzasVar = this.zzb.zzf;
        zzasVar.zzs(this.zza);
        zzagVar = zzaw.zza;
        zzagVar.zzd("onGetChunkFileDescriptor", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.zzw
    public final void zzf(int i, Bundle bundle) {
        com.google.android.play.core.internal.zzas zzasVar;
        com.google.android.play.core.internal.zzag zzagVar;
        zzasVar = this.zzb.zzf;
        zzasVar.zzs(this.zza);
        zzagVar = zzaw.zza;
        zzagVar.zzd("onGetSession(%d)", Integer.valueOf(i));
    }

    @Override // com.google.android.play.core.internal.zzw
    public void zzg(List list) {
        com.google.android.play.core.internal.zzas zzasVar;
        com.google.android.play.core.internal.zzag zzagVar;
        zzasVar = this.zzb.zzf;
        zzasVar.zzs(this.zza);
        zzagVar = zzaw.zza;
        zzagVar.zzd("onGetSessionStates", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.zzw
    public void zzh(Bundle bundle, Bundle bundle2) {
        com.google.android.play.core.internal.zzas zzasVar;
        com.google.android.play.core.internal.zzag zzagVar;
        zzasVar = this.zzb.zzg;
        zzasVar.zzs(this.zza);
        zzagVar = zzaw.zza;
        zzagVar.zzd("onKeepAlive(%b)", Boolean.valueOf(bundle.getBoolean("keep_alive")));
    }

    @Override // com.google.android.play.core.internal.zzw
    public final void zzi(Bundle bundle, Bundle bundle2) {
        com.google.android.play.core.internal.zzas zzasVar;
        com.google.android.play.core.internal.zzag zzagVar;
        zzasVar = this.zzb.zzf;
        zzasVar.zzs(this.zza);
        zzagVar = zzaw.zza;
        zzagVar.zzd("onNotifyChunkTransferred(%s, %s, %d, session=%d)", bundle.getString("module_name"), bundle.getString("slice_id"), Integer.valueOf(bundle.getInt("chunk_number")), Integer.valueOf(bundle.getInt("session_id")));
    }

    @Override // com.google.android.play.core.internal.zzw
    public final void zzj(Bundle bundle, Bundle bundle2) {
        com.google.android.play.core.internal.zzas zzasVar;
        com.google.android.play.core.internal.zzag zzagVar;
        zzasVar = this.zzb.zzf;
        zzasVar.zzs(this.zza);
        zzagVar = zzaw.zza;
        zzagVar.zzd("onNotifyModuleCompleted(%s, sessionId=%d)", bundle.getString("module_name"), Integer.valueOf(bundle.getInt("session_id")));
    }

    @Override // com.google.android.play.core.internal.zzw
    public final void zzk(Bundle bundle, Bundle bundle2) {
        com.google.android.play.core.internal.zzas zzasVar;
        com.google.android.play.core.internal.zzag zzagVar;
        zzasVar = this.zzb.zzf;
        zzasVar.zzs(this.zza);
        zzagVar = zzaw.zza;
        zzagVar.zzd("onNotifySessionFailed(%d)", Integer.valueOf(bundle.getInt("session_id")));
    }

    @Override // com.google.android.play.core.internal.zzw
    public final void zzl(Bundle bundle, Bundle bundle2) {
        com.google.android.play.core.internal.zzas zzasVar;
        com.google.android.play.core.internal.zzag zzagVar;
        zzasVar = this.zzb.zzf;
        zzasVar.zzs(this.zza);
        zzagVar = zzaw.zza;
        zzagVar.zzd("onRemoveModule()", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.zzw
    public void zzm(Bundle bundle, Bundle bundle2) {
        com.google.android.play.core.internal.zzas zzasVar;
        com.google.android.play.core.internal.zzag zzagVar;
        zzasVar = this.zzb.zzf;
        zzasVar.zzs(this.zza);
        zzagVar = zzaw.zza;
        zzagVar.zzd("onRequestDownloadInfo()", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.zzw
    public void zzn(int i, Bundle bundle) {
        com.google.android.play.core.internal.zzas zzasVar;
        com.google.android.play.core.internal.zzag zzagVar;
        zzasVar = this.zzb.zzf;
        zzasVar.zzs(this.zza);
        zzagVar = zzaw.zza;
        zzagVar.zzd("onStartDownload(%d)", Integer.valueOf(i));
    }
}
