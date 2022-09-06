package com.google.android.gms.internal.vision;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
/* loaded from: classes2.dex */
public final class zzu extends zza implements zzt {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
    }

    @Override // com.google.android.gms.internal.vision.zzt
    public final zzx[] zza(IObjectWrapper iObjectWrapper, zzm zzmVar, zzz zzzVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        zzc.zza(obtainAndWriteInterfaceToken, zzmVar);
        zzc.zza(obtainAndWriteInterfaceToken, zzzVar);
        Parcel transactAndReadException = transactAndReadException(3, obtainAndWriteInterfaceToken);
        zzx[] zzxVarArr = (zzx[]) transactAndReadException.createTypedArray(zzx.CREATOR);
        transactAndReadException.recycle();
        return zzxVarArr;
    }

    @Override // com.google.android.gms.internal.vision.zzt
    public final void zzq() throws RemoteException {
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken());
    }
}
