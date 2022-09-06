package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import android.os.RemoteException;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzba extends zzbb {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzba(zzbc zzbcVar, com.google.android.play.core.tasks.zzi zziVar) {
        super(zzbcVar, zziVar);
    }

    @Override // com.google.android.play.core.splitinstall.zzbb, com.google.android.play.core.internal.zzcc
    public final void zzi(int i, Bundle bundle) throws RemoteException {
        super.zzi(i, bundle);
        this.zza.zze(Integer.valueOf(i));
    }
}
