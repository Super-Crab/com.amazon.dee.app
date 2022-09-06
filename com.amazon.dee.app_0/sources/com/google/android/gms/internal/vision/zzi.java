package com.google.android.gms.internal.vision;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.vision.barcode.Barcode;
/* loaded from: classes2.dex */
public final class zzi extends zza implements zzh {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzi(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
    }

    @Override // com.google.android.gms.internal.vision.zzh
    public final Barcode[] zza(IObjectWrapper iObjectWrapper, zzm zzmVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        zzc.zza(obtainAndWriteInterfaceToken, zzmVar);
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
        Barcode[] barcodeArr = (Barcode[]) transactAndReadException.createTypedArray(Barcode.CREATOR);
        transactAndReadException.recycle();
        return barcodeArr;
    }

    @Override // com.google.android.gms.internal.vision.zzh
    public final Barcode[] zzb(IObjectWrapper iObjectWrapper, zzm zzmVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        zzc.zza(obtainAndWriteInterfaceToken, zzmVar);
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken);
        Barcode[] barcodeArr = (Barcode[]) transactAndReadException.createTypedArray(Barcode.CREATOR);
        transactAndReadException.recycle();
        return barcodeArr;
    }

    @Override // com.google.android.gms.internal.vision.zzh
    public final void zzn() throws RemoteException {
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken());
    }
}
