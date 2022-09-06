package com.google.android.gms.internal.vision;

import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzbk {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i, byte[] bArr, int i2, int i3, zzbl zzblVar) throws zzcx {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                return zzb(bArr, i2, zzblVar);
            }
            if (i4 == 1) {
                return i2 + 8;
            }
            if (i4 == 2) {
                return zza(bArr, i2, zzblVar) + zzblVar.zzgo;
            }
            if (i4 != 3) {
                if (i4 != 5) {
                    throw zzcx.zzcd();
                }
                return i2 + 4;
            }
            int i5 = (i & (-8)) | 4;
            int i6 = 0;
            while (i2 < i3) {
                i2 = zza(bArr, i2, zzblVar);
                i6 = zzblVar.zzgo;
                if (i6 == i5) {
                    break;
                }
                i2 = zza(i6, bArr, i2, i3, zzblVar);
            }
            if (i2 <= i3 && i6 == i5) {
                return i2;
            }
            throw zzcx.zzcf();
        }
        throw zzcx.zzcd();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i, byte[] bArr, int i2, int i3, zzcw<?> zzcwVar, zzbl zzblVar) {
        zzcs zzcsVar = (zzcs) zzcwVar;
        int zza = zza(bArr, i2, zzblVar);
        while (true) {
            zzcsVar.zzae(zzblVar.zzgo);
            if (zza >= i3) {
                break;
            }
            int zza2 = zza(bArr, zza, zzblVar);
            if (i != zzblVar.zzgo) {
                break;
            }
            zza = zza(bArr, zza2, zzblVar);
        }
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i, byte[] bArr, int i2, int i3, zzfg zzfgVar, zzbl zzblVar) throws zzcx {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int zzb = zzb(bArr, i2, zzblVar);
                zzfgVar.zzb(i, Long.valueOf(zzblVar.zzgp));
                return zzb;
            } else if (i4 == 1) {
                zzfgVar.zzb(i, Long.valueOf(zzb(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int zza = zza(bArr, i2, zzblVar);
                int i5 = zzblVar.zzgo;
                if (i5 < 0) {
                    throw zzcx.zzcc();
                }
                zzfgVar.zzb(i, i5 == 0 ? zzbo.zzgt : zzbo.zzb(bArr, zza, i5));
                return zza + i5;
            } else if (i4 != 3) {
                if (i4 != 5) {
                    throw zzcx.zzcd();
                }
                zzfgVar.zzb(i, Integer.valueOf(zza(bArr, i2)));
                return i2 + 4;
            } else {
                zzfg zzdv = zzfg.zzdv();
                int i6 = (i & (-8)) | 4;
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int zza2 = zza(bArr, i2, zzblVar);
                    int i8 = zzblVar.zzgo;
                    i7 = i8;
                    if (i8 == i6) {
                        i2 = zza2;
                        break;
                    }
                    int zza3 = zza(i7, bArr, zza2, i3, zzdv, zzblVar);
                    i7 = i8;
                    i2 = zza3;
                }
                if (i2 > i3 || i7 != i6) {
                    throw zzcx.zzcf();
                }
                zzfgVar.zzb(i, zzdv);
                return i2;
            }
        }
        throw zzcx.zzcd();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i, byte[] bArr, int i2, zzbl zzblVar) {
        int i3;
        int i4;
        int i5 = i & 127;
        int i6 = i2 + 1;
        byte b = bArr[i2];
        if (b < 0) {
            int i7 = i5 | ((b & Byte.MAX_VALUE) << 7);
            int i8 = i6 + 1;
            byte b2 = bArr[i6];
            if (b2 >= 0) {
                i3 = b2 << 14;
            } else {
                i5 = i7 | ((b2 & Byte.MAX_VALUE) << 14);
                i6 = i8 + 1;
                byte b3 = bArr[i8];
                if (b3 >= 0) {
                    i4 = b3 << 21;
                } else {
                    i7 = i5 | ((b3 & Byte.MAX_VALUE) << 21);
                    i8 = i6 + 1;
                    byte b4 = bArr[i6];
                    if (b4 >= 0) {
                        i3 = b4 << 28;
                    } else {
                        int i9 = i7 | ((b4 & Byte.MAX_VALUE) << 28);
                        while (true) {
                            int i10 = i8 + 1;
                            if (bArr[i8] >= 0) {
                                zzblVar.zzgo = i9;
                                return i10;
                            }
                            i8 = i10;
                        }
                    }
                }
            }
            zzblVar.zzgo = i7 | i3;
            return i8;
        }
        i4 = b << 7;
        zzblVar.zzgo = i5 | i4;
        return i6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(byte[] bArr, int i, zzbl zzblVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b >= 0) {
            zzblVar.zzgo = b;
            return i2;
        }
        return zza(b, bArr, i2, zzblVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(byte[] bArr, int i, zzcw<?> zzcwVar, zzbl zzblVar) throws IOException {
        zzcs zzcsVar = (zzcs) zzcwVar;
        int zza = zza(bArr, i, zzblVar);
        int i2 = zzblVar.zzgo + zza;
        while (zza < i2) {
            zza = zza(bArr, zza, zzblVar);
            zzcsVar.zzae(zzblVar.zzgo);
        }
        if (zza == i2) {
            return zza;
        }
        throw zzcx.zzcb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(byte[] bArr, int i, zzbl zzblVar) {
        byte b;
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            zzblVar.zzgp = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b2 = bArr[i2];
        long j2 = (j & 127) | ((b2 & Byte.MAX_VALUE) << 7);
        int i4 = 7;
        while (b2 < 0) {
            int i5 = i3 + 1;
            i4 += 7;
            j2 |= (b & Byte.MAX_VALUE) << i4;
            b2 = bArr[i3];
            i3 = i5;
        }
        zzblVar.zzgp = j2;
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long zzb(byte[] bArr, int i) {
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double zzc(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzb(bArr, i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(byte[] bArr, int i, zzbl zzblVar) throws zzcx {
        int zza = zza(bArr, i, zzblVar);
        int i2 = zzblVar.zzgo;
        if (i2 >= 0) {
            if (i2 == 0) {
                zzblVar.zzgq = "";
                return zza;
            }
            zzblVar.zzgq = new String(bArr, zza, i2, zzct.UTF_8);
            return zza + i2;
        }
        throw zzcx.zzcc();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float zzd(byte[] bArr, int i) {
        return Float.intBitsToFloat(zza(bArr, i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(byte[] bArr, int i, zzbl zzblVar) throws zzcx {
        int zza = zza(bArr, i, zzblVar);
        int i2 = zzblVar.zzgo;
        if (i2 >= 0) {
            if (i2 == 0) {
                zzblVar.zzgq = "";
                return zza;
            }
            int i3 = zza + i2;
            if (!zzfn.zze(bArr, zza, i3)) {
                throw zzcx.zzcg();
            }
            zzblVar.zzgq = new String(bArr, zza, i2, zzct.UTF_8);
            return i3;
        }
        throw zzcx.zzcc();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(byte[] bArr, int i, zzbl zzblVar) throws zzcx {
        int zza = zza(bArr, i, zzblVar);
        int i2 = zzblVar.zzgo;
        if (i2 >= 0) {
            if (i2 == 0) {
                zzblVar.zzgq = zzbo.zzgt;
                return zza;
            }
            zzblVar.zzgq = zzbo.zzb(bArr, zza, i2);
            return zza + i2;
        }
        throw zzcx.zzcc();
    }
}
