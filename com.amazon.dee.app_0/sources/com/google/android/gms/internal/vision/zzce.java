package com.google.android.gms.internal.vision;
/* loaded from: classes2.dex */
final class zzce {
    private static final Class<?> zzhn = zzbd();

    private static Class<?> zzbd() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static zzcf zzbe() {
        Class<?> cls = zzhn;
        if (cls != null) {
            try {
                return (zzcf) cls.getDeclaredMethod("getEmptyRegistry", new Class[0]).invoke(null, new Object[0]);
            } catch (Exception unused) {
            }
        }
        return zzcf.zzhq;
    }
}
