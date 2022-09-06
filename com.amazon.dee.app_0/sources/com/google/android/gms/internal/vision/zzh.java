package com.google.android.gms.internal.vision;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.vision.barcode.Barcode;
/* loaded from: classes2.dex */
public interface zzh extends IInterface {
    Barcode[] zza(IObjectWrapper iObjectWrapper, zzm zzmVar) throws RemoteException;

    Barcode[] zzb(IObjectWrapper iObjectWrapper, zzm zzmVar) throws RemoteException;

    void zzn() throws RemoteException;
}
