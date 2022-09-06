package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzcr;
import java.io.IOException;
import java.util.Arrays;
/* loaded from: classes2.dex */
public final class zzfg {
    private static final zzfg zzot = new zzfg(0, new int[0], new Object[0], false);
    private int count;
    private boolean zzgl;
    private int zzks;
    private Object[] zznf;
    private int[] zzou;

    private zzfg() {
        this(0, new int[8], new Object[8], true);
    }

    private zzfg(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzks = -1;
        this.count = i;
        this.zzou = iArr;
        this.zznf = objArr;
        this.zzgl = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzfg zza(zzfg zzfgVar, zzfg zzfgVar2) {
        int i = zzfgVar.count + zzfgVar2.count;
        int[] copyOf = Arrays.copyOf(zzfgVar.zzou, i);
        System.arraycopy(zzfgVar2.zzou, 0, copyOf, zzfgVar.count, zzfgVar2.count);
        Object[] copyOf2 = Arrays.copyOf(zzfgVar.zznf, i);
        System.arraycopy(zzfgVar2.zznf, 0, copyOf2, zzfgVar.count, zzfgVar2.count);
        return new zzfg(i, copyOf, copyOf2, true);
    }

    private static void zzb(int i, Object obj, zzfz zzfzVar) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            zzfzVar.zzi(i2, ((Long) obj).longValue());
        } else if (i3 == 1) {
            zzfzVar.zzc(i2, ((Long) obj).longValue());
        } else if (i3 == 2) {
            zzfzVar.zza(i2, (zzbo) obj);
        } else if (i3 != 3) {
            if (i3 != 5) {
                throw new RuntimeException(zzcx.zzce());
            }
            zzfzVar.zzh(i2, ((Integer) obj).intValue());
        } else if (zzfzVar.zzbc() == zzcr.zzd.zzlj) {
            zzfzVar.zzac(i2);
            ((zzfg) obj).zzb(zzfzVar);
            zzfzVar.zzad(i2);
        } else {
            zzfzVar.zzad(i2);
            ((zzfg) obj).zzb(zzfzVar);
            zzfzVar.zzac(i2);
        }
    }

    public static zzfg zzdu() {
        return zzot;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzfg zzdv() {
        return new zzfg();
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzfg)) {
            return false;
        }
        zzfg zzfgVar = (zzfg) obj;
        int i = this.count;
        if (i == zzfgVar.count) {
            int[] iArr = this.zzou;
            int[] iArr2 = zzfgVar.zzou;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    z = true;
                    break;
                } else if (iArr[i2] != iArr2[i2]) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
            if (z) {
                Object[] objArr = this.zznf;
                Object[] objArr2 = zzfgVar.zznf;
                int i3 = this.count;
                int i4 = 0;
                while (true) {
                    if (i4 >= i3) {
                        z2 = true;
                        break;
                    } else if (!objArr[i4].equals(objArr2[i4])) {
                        z2 = false;
                        break;
                    } else {
                        i4++;
                    }
                }
                if (z2) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = this.count;
        int i2 = (i + 527) * 31;
        int[] iArr = this.zzou;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.zznf;
        int i7 = this.count;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(zzfz zzfzVar) throws IOException {
        if (zzfzVar.zzbc() == zzcr.zzd.zzlk) {
            for (int i = this.count - 1; i >= 0; i--) {
                zzfzVar.zza(this.zzou[i] >>> 3, this.zznf[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.count; i2++) {
            zzfzVar.zza(this.zzou[i2] >>> 3, this.zznf[i2]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzea.zza(sb, i, String.valueOf(this.zzou[i2] >>> 3), this.zznf[i2]);
        }
    }

    public final void zzao() {
        this.zzgl = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(int i, Object obj) {
        if (this.zzgl) {
            int i2 = this.count;
            if (i2 == this.zzou.length) {
                int i3 = this.count + (i2 < 4 ? 8 : i2 >> 1);
                this.zzou = Arrays.copyOf(this.zzou, i3);
                this.zznf = Arrays.copyOf(this.zznf, i3);
            }
            int[] iArr = this.zzou;
            int i4 = this.count;
            iArr[i4] = i;
            this.zznf[i4] = obj;
            this.count = i4 + 1;
            return;
        }
        throw new UnsupportedOperationException();
    }

    public final void zzb(zzfz zzfzVar) throws IOException {
        if (this.count == 0) {
            return;
        }
        if (zzfzVar.zzbc() == zzcr.zzd.zzlj) {
            for (int i = 0; i < this.count; i++) {
                zzb(this.zzou[i], this.zznf[i], zzfzVar);
            }
            return;
        }
        for (int i2 = this.count - 1; i2 >= 0; i2--) {
            zzb(this.zzou[i2], this.zznf[i2], zzfzVar);
        }
    }

    public final int zzbl() {
        int zze;
        int i = this.zzks;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.count; i3++) {
            int i4 = this.zzou[i3];
            int i5 = i4 >>> 3;
            int i6 = i4 & 7;
            if (i6 == 0) {
                zze = zzca.zze(i5, ((Long) this.zznf[i3]).longValue());
            } else if (i6 == 1) {
                zze = zzca.zzg(i5, ((Long) this.zznf[i3]).longValue());
            } else if (i6 == 2) {
                zze = zzca.zzc(i5, (zzbo) this.zznf[i3]);
            } else if (i6 == 3) {
                i2 = ((zzfg) this.zznf[i3]).zzbl() + (zzca.zzt(i5) << 1) + i2;
            } else if (i6 != 5) {
                throw new IllegalStateException(zzcx.zzce());
            } else {
                zze = zzca.zzl(i5, ((Integer) this.zznf[i3]).intValue());
            }
            i2 = zze + i2;
        }
        this.zzks = i2;
        return i2;
    }

    public final int zzdw() {
        int i = this.zzks;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.count; i3++) {
            i2 += zzca.zzd(this.zzou[i3] >>> 3, (zzbo) this.zznf[i3]);
        }
        this.zzks = i2;
        return i2;
    }
}
