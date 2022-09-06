package com.google.android.gms.internal.vision;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.vision.barcode.Barcode;
import java.nio.ByteBuffer;
/* loaded from: classes2.dex */
public final class zzg extends zzl<zzh> {
    private final zze zzbm;

    public zzg(Context context, zze zzeVar) {
        super(context, "BarcodeNativeHandle", "barcode");
        this.zzbm = zzeVar;
        zzp();
    }

    @Override // com.google.android.gms.internal.vision.zzl
    protected final /* synthetic */ zzh zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, DynamiteModule.LoadingException {
        zzj zzkVar;
        IBinder instantiate = dynamiteModule.instantiate("com.google.android.gms.vision.barcode.ChimeraNativeBarcodeDetectorCreator");
        if (instantiate == null) {
            zzkVar = null;
        } else {
            IInterface queryLocalInterface = instantiate.queryLocalInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetectorCreator");
            zzkVar = queryLocalInterface instanceof zzj ? (zzj) queryLocalInterface : new zzk(instantiate);
        }
        if (zzkVar == null) {
            return null;
        }
        return zzkVar.zza(ObjectWrapper.wrap(context), this.zzbm);
    }

    public final Barcode[] zza(Bitmap bitmap, zzm zzmVar) {
        if (!isOperational()) {
            return new Barcode[0];
        }
        try {
            return zzp().zzb(ObjectWrapper.wrap(bitmap), zzmVar);
        } catch (RemoteException e) {
            Log.e("BarcodeNativeHandle", "Error calling native barcode detector", e);
            return new Barcode[0];
        }
    }

    public final Barcode[] zza(ByteBuffer byteBuffer, zzm zzmVar) {
        if (!isOperational()) {
            return new Barcode[0];
        }
        try {
            return zzp().zza(ObjectWrapper.wrap(byteBuffer), zzmVar);
        } catch (RemoteException e) {
            Log.e("BarcodeNativeHandle", "Error calling native barcode detector", e);
            return new Barcode[0];
        }
    }

    @Override // com.google.android.gms.internal.vision.zzl
    protected final void zzm() throws RemoteException {
        if (isOperational()) {
            zzp().zzn();
        }
    }
}
