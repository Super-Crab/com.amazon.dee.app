package com.google.android.gms.internal.vision;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
/* loaded from: classes2.dex */
public final class zzad extends zzl<zzt> {
    private final zzae zzdg;

    public zzad(Context context, zzae zzaeVar) {
        super(context, "TextNativeHandle", "text");
        this.zzdg = zzaeVar;
        zzp();
    }

    @Override // com.google.android.gms.internal.vision.zzl
    protected final /* synthetic */ zzt zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, DynamiteModule.LoadingException {
        zzv zzwVar;
        IBinder instantiate = dynamiteModule.instantiate("com.google.android.gms.vision.text.ChimeraNativeTextRecognizerCreator");
        if (instantiate == null) {
            zzwVar = null;
        } else {
            IInterface queryLocalInterface = instantiate.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator");
            zzwVar = queryLocalInterface instanceof zzv ? (zzv) queryLocalInterface : new zzw(instantiate);
        }
        if (zzwVar == null) {
            return null;
        }
        return zzwVar.zza(ObjectWrapper.wrap(context), this.zzdg);
    }

    public final zzx[] zza(Bitmap bitmap, zzm zzmVar, zzz zzzVar) {
        if (!isOperational()) {
            return new zzx[0];
        }
        try {
            return zzp().zza(ObjectWrapper.wrap(bitmap), zzmVar, zzzVar);
        } catch (RemoteException e) {
            Log.e("TextNativeHandle", "Error calling native text recognizer", e);
            return new zzx[0];
        }
    }

    @Override // com.google.android.gms.internal.vision.zzl
    protected final void zzm() throws RemoteException {
        zzp().zzq();
    }
}
