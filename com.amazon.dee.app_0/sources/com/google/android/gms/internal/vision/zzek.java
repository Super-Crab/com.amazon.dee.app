package com.google.android.gms.internal.vision;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzek {
    private static final zzek zznx = new zzek();
    private final zzeo zzny;
    private final ConcurrentMap<Class<?>, zzen<?>> zznz = new ConcurrentHashMap();

    private zzek() {
        String[] strArr = {"com.google.protobuf.AndroidProto3SchemaFactory"};
        zzeo zzeoVar = null;
        for (int i = 0; i <= 0; i++) {
            zzeoVar = zzk(strArr[0]);
            if (zzeoVar != null) {
                break;
            }
        }
        this.zzny = zzeoVar == null ? new zzdm() : zzeoVar;
    }

    public static zzek zzdc() {
        return zznx;
    }

    private static zzeo zzk(String str) {
        try {
            return (zzeo) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable unused) {
            return null;
        }
    }

    public final <T> zzen<T> zze(Class<T> cls) {
        zzct.zza(cls, "messageType");
        zzen<T> zzenVar = (zzen<T>) this.zznz.get(cls);
        if (zzenVar == null) {
            zzen<T> zzd = this.zzny.zzd(cls);
            zzct.zza(cls, "messageType");
            zzct.zza(zzd, "schema");
            zzen<T> zzenVar2 = (zzen<T>) this.zznz.putIfAbsent(cls, zzd);
            return zzenVar2 != null ? zzenVar2 : zzd;
        }
        return zzenVar;
    }

    public final <T> zzen<T> zzq(T t) {
        return zze(t.getClass());
    }
}
