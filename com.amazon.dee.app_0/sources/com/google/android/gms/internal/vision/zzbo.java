package com.google.android.gms.internal.vision;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Iterator;
/* loaded from: classes2.dex */
public abstract class zzbo implements Serializable, Iterable<Byte> {
    public static final zzbo zzgt = new zzbv(zzct.zzlo);
    private static final zzbs zzgu;
    private int zzgv = 0;

    static {
        zzgu = zzbj.zzaq() ? new zzbw(null) : new zzbq(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) < 0) {
            if (i >= 0) {
                if (i2 >= i) {
                    throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline28(37, "End index: ", i2, " >= ", i3));
                }
                throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline28(66, "Beginning index larger than ending index: ", i, ", ", i2));
            }
            StringBuilder sb = new StringBuilder(32);
            sb.append("Beginning index: ");
            sb.append(i);
            sb.append(" < 0");
            throw new IndexOutOfBoundsException(sb.toString());
        }
        return i4;
    }

    public static zzbo zzb(byte[] bArr, int i, int i2) {
        return new zzbv(zzgu.zzc(bArr, i, i2));
    }

    public static zzbo zzg(String str) {
        return new zzbv(str.getBytes(zzct.UTF_8));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzbt zzm(int i) {
        return new zzbt(i, null);
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i = this.zzgv;
        if (i == 0) {
            int size = size();
            i = zza(size, 0, size);
            if (i == 0) {
                i = 1;
            }
            this.zzgv = i;
        }
        return i;
    }

    @Override // java.lang.Iterable
    public /* synthetic */ Iterator<Byte> iterator() {
        return new zzbp(this);
    }

    public abstract int size();

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()));
    }

    protected abstract int zza(int i, int i2, int i3);

    protected abstract String zza(Charset charset);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(zzbn zzbnVar) throws IOException;

    public final String zzas() {
        return size() == 0 ? "" : zza(zzct.UTF_8);
    }

    public abstract boolean zzat();

    /* JADX INFO: Access modifiers changed from: protected */
    public final int zzau() {
        return this.zzgv;
    }

    public abstract zzbo zzc(int i, int i2);

    public abstract byte zzl(int i);
}
