package com.google.android.gms.internal.vision;
/* loaded from: classes2.dex */
final class zzbj {
    private static final Class<?> zzgm = zzf("libcore.io.Memory");
    private static final boolean zzgn;

    static {
        zzgn = zzf("org.robolectric.Robolectric") != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzaq() {
        return zzgm != null && !zzgn;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Class<?> zzar() {
        return zzgm;
    }

    private static <T> Class<T> zzf(String str) {
        try {
            return (Class<T>) Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }
}
