package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzbf;
import com.google.android.gms.internal.vision.zzbg;
/* loaded from: classes2.dex */
public abstract class zzbg<MessageType extends zzbf<MessageType, BuilderType>, BuilderType extends zzbg<MessageType, BuilderType>> implements zzdy {
    protected abstract BuilderType zza(MessageType messagetype);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.vision.zzdy
    public final /* synthetic */ zzdy zza(zzdx zzdxVar) {
        if (zzbw().getClass().isInstance(zzdxVar)) {
            return zza((zzbg<MessageType, BuilderType>) ((zzbf) zzdxVar));
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }

    @Override // 
    /* renamed from: zzam */
    public abstract BuilderType clone();
}
