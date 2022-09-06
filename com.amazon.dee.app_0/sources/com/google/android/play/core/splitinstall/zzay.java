package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import android.os.RemoteException;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzay extends zzbb {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzay(zzbc zzbcVar, com.google.android.play.core.tasks.zzi zziVar) {
        super(zzbcVar, zziVar);
    }

    @Override // com.google.android.play.core.splitinstall.zzbb, com.google.android.play.core.internal.zzcc
    public final void zzg(int i, Bundle bundle) throws RemoteException {
        super.zzg(i, bundle);
        this.zza.zze(SplitInstallSessionState.zzd(bundle));
    }
}
