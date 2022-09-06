package com.google.android.play.core.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzar implements ServiceConnection {
    final /* synthetic */ zzas zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzar(zzas zzasVar, zzaq zzaqVar) {
        this.zza = zzasVar;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        zzag zzagVar;
        zzagVar = this.zza.zzc;
        zzagVar.zzd("ServiceConnectionImpl.onServiceConnected(%s)", componentName);
        this.zza.zzc().post(new zzao(this, iBinder));
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        zzag zzagVar;
        zzagVar = this.zza.zzc;
        zzagVar.zzd("ServiceConnectionImpl.onServiceDisconnected(%s)", componentName);
        this.zza.zzc().post(new zzap(this));
    }
}
