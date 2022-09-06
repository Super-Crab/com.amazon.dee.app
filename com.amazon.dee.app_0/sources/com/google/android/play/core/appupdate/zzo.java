package com.google.android.play.core.appupdate;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.install.InstallException;
import com.google.android.play.core.internal.zzag;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzo extends zzn {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(zzq zzqVar, com.google.android.play.core.tasks.zzi zziVar) {
        super(zzqVar, new zzag("OnCompleteUpdateCallback"), zziVar);
    }

    @Override // com.google.android.play.core.appupdate.zzn, com.google.android.play.core.internal.zzr
    public final void zzb(Bundle bundle) throws RemoteException {
        int i;
        int i2;
        super.zzb(bundle);
        i = bundle.getInt("error.code", -2);
        if (i != 0) {
            com.google.android.play.core.tasks.zzi zziVar = this.zzb;
            i2 = bundle.getInt("error.code", -2);
            zziVar.zzd(new InstallException(i2));
            return;
        }
        this.zzb.zze(null);
    }
}
