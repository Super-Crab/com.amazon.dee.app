package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzx extends com.google.android.play.core.listener.zzc {
    @Nullable
    private static zzx zzc;
    private final Handler zzd;
    private final zzg zze;
    private final Set zzf;

    @VisibleForTesting
    public zzx(Context context, zzg zzgVar) {
        super(new com.google.android.play.core.internal.zzag("SplitInstallListenerRegistry"), new IntentFilter("com.google.android.play.core.splitinstall.receiver.SplitInstallUpdateIntentService"), context);
        this.zzd = new Handler(Looper.getMainLooper());
        this.zzf = new LinkedHashSet();
        this.zze = zzgVar;
    }

    public static synchronized zzx zzc(Context context) {
        zzx zzxVar;
        synchronized (zzx.class) {
            if (zzc == null) {
                zzc = new zzx(context, zzo.INSTANCE);
            }
            zzxVar = zzc;
        }
        return zzxVar;
    }

    @Override // com.google.android.play.core.listener.zzc
    protected final void zza(Context context, Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra("session_state");
        if (bundleExtra == null) {
            return;
        }
        SplitInstallSessionState zzd = SplitInstallSessionState.zzd(bundleExtra);
        this.zza.zza("ListenerRegistryBroadcastReceiver.onReceive: %s", zzd);
        zzh zza = this.zze.zza();
        if (zzd.status() == 3 && zza != null) {
            zza.zzd(zzd.zzc(), new zzv(this, zzd, intent, context));
        } else {
            zzm(zzd);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzk(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzf.add(splitInstallStateUpdatedListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzl(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzf.remove(splitInstallStateUpdatedListener);
    }

    public final synchronized void zzm(SplitInstallSessionState splitInstallSessionState) {
        Iterator it2 = new LinkedHashSet(this.zzf).iterator();
        while (it2.hasNext()) {
            ((SplitInstallStateUpdatedListener) it2.next()).onStateUpdate(splitInstallSessionState);
        }
        super.zzi(splitInstallSessionState);
    }
}
