package com.google.android.play.core.appupdate;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzd extends ResultReceiver {
    final /* synthetic */ com.google.android.play.core.tasks.zzi zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzd(zzf zzfVar, Handler handler, com.google.android.play.core.tasks.zzi zziVar) {
        super(handler);
        this.zza = zziVar;
    }

    @Override // android.os.ResultReceiver
    public final void onReceiveResult(int i, Bundle bundle) {
        if (i == 1) {
            this.zza.zze(-1);
        } else if (i != 2) {
            this.zza.zze(1);
        } else {
            this.zza.zze(0);
        }
    }
}
