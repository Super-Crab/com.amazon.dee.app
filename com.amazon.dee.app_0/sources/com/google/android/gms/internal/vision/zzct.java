package com.google.android.gms.internal.vision;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
/* loaded from: classes2.dex */
public final class zzct {
    public static final byte[] zzlo;
    private static final ByteBuffer zzlp;
    private static final zzbx zzlq;
    static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");

    static {
        byte[] bArr = new byte[0];
        zzlo = bArr;
        zzlp = ByteBuffer.wrap(bArr);
        byte[] bArr2 = zzlo;
        zzlq = zzbx.zza(bArr2, 0, bArr2.length, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    public static int hashCode(byte[] bArr) {
        int length = bArr.length;
        int zza = zza(length, bArr, 0, length);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i, byte[] bArr, int i2, int i3) {
        int i4 = i;
        for (int i5 = i2; i5 < i2 + i3; i5++) {
            i4 = (i4 * 31) + bArr[i5];
        }
        return i4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zza(Object obj, Object obj2) {
        return ((zzdx) obj).zzbu().zza((zzdx) obj2).zzbz();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T zza(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static int zzc(boolean z) {
        return z ? 1231 : 1237;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzf(zzdx zzdxVar) {
        return false;
    }

    public static boolean zzf(byte[] bArr) {
        return zzfn.zzf(bArr);
    }

    public static String zzg(byte[] bArr) {
        return new String(bArr, UTF_8);
    }

    public static int zzk(long j) {
        return (int) (j ^ (j >>> 32));
    }
}
