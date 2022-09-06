package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzaz extends zzbb {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaz(zzbc zzbcVar, com.google.android.play.core.tasks.zzi zziVar) {
        super(zzbcVar, zziVar);
    }

    @Override // com.google.android.play.core.splitinstall.zzbb, com.google.android.play.core.internal.zzcc
    public final void zzh(List list) throws RemoteException {
        super.zzh(list);
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add(SplitInstallSessionState.zzd((Bundle) it2.next()));
        }
        this.zza.zze(arrayList);
    }
}
