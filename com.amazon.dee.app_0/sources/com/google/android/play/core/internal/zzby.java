package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzby extends zzk implements zzca {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzby(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.splitinstall.protocol.ISplitInstallService");
    }

    @Override // com.google.android.play.core.internal.zzca
    public final void zzc(String str, int i, Bundle bundle, zzcc zzccVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeInt(i);
        zzm.zzb(zza, bundle);
        zzm.zzc(zza, zzccVar);
        zzb(4, zza);
    }

    @Override // com.google.android.play.core.internal.zzca
    public final void zzd(String str, List list, Bundle bundle, zzcc zzccVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeTypedList(list);
        zzm.zzb(zza, bundle);
        zzm.zzc(zza, zzccVar);
        zzb(8, zza);
    }

    @Override // com.google.android.play.core.internal.zzca
    public final void zze(String str, List list, Bundle bundle, zzcc zzccVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeTypedList(list);
        zzm.zzb(zza, bundle);
        zzm.zzc(zza, zzccVar);
        zzb(13, zza);
    }

    @Override // com.google.android.play.core.internal.zzca
    public final void zzf(String str, List list, Bundle bundle, zzcc zzccVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeTypedList(list);
        zzm.zzb(zza, bundle);
        zzm.zzc(zza, zzccVar);
        zzb(14, zza);
    }

    @Override // com.google.android.play.core.internal.zzca
    public final void zzg(String str, List list, Bundle bundle, zzcc zzccVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeTypedList(list);
        zzm.zzb(zza, bundle);
        zzm.zzc(zza, zzccVar);
        zzb(7, zza);
    }

    @Override // com.google.android.play.core.internal.zzca
    public final void zzh(String str, int i, zzcc zzccVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeInt(i);
        zzm.zzc(zza, zzccVar);
        zzb(5, zza);
    }

    @Override // com.google.android.play.core.internal.zzca
    public final void zzi(String str, zzcc zzccVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzm.zzc(zza, zzccVar);
        zzb(6, zza);
    }

    @Override // com.google.android.play.core.internal.zzca
    public final void zzj(String str, List list, Bundle bundle, zzcc zzccVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeTypedList(list);
        zzm.zzb(zza, bundle);
        zzm.zzc(zza, zzccVar);
        zzb(2, zza);
    }
}
