package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.util.PlatformVersion;
/* loaded from: classes2.dex */
public abstract class zab {
    private final int type;

    public zab(int i) {
        this.type = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Status zaa(RemoteException remoteException) {
        StringBuilder sb = new StringBuilder();
        if (PlatformVersion.isAtLeastIceCreamSandwichMR1() && (remoteException instanceof TransactionTooLargeException)) {
            sb.append("TransactionTooLargeException: ");
        }
        sb.append(remoteException.getLocalizedMessage());
        return new Status(8, sb.toString());
    }

    public abstract void zaa(@NonNull Status status);

    public abstract void zaa(GoogleApiManager.zaa<?> zaaVar) throws DeadObjectException;

    public abstract void zaa(@NonNull zaab zaabVar, boolean z);

    public abstract void zaa(@NonNull RuntimeException runtimeException);
}
