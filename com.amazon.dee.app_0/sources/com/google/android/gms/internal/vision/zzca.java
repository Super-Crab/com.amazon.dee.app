package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes2.dex */
public abstract class zzca extends zzbn {
    private static final Logger logger = Logger.getLogger(zzca.class.getName());
    private static final boolean zzhj = zzfl.zzdx();
    zzcc zzhk;

    /* loaded from: classes2.dex */
    static class zza extends zzca {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zza(byte[] bArr, int i, int i2) {
            super();
            if (bArr != null) {
                int i3 = i2 + 0;
                if ((i2 | 0 | (bArr.length - i3)) < 0) {
                    throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), 0, Integer.valueOf(i2)));
                }
                this.buffer = bArr;
                this.offset = 0;
                this.position = 0;
                this.limit = i3;
                return;
            }
            throw new NullPointerException("buffer");
        }

        private final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.buffer, this.position, i2);
                this.position += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)), e);
            }
        }

        @Override // com.google.android.gms.internal.vision.zzca
        public final void zza(byte b) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.vision.zzca
        public final void zza(int i, long j) throws IOException {
            zzd(i, 0);
            zzb(j);
        }

        @Override // com.google.android.gms.internal.vision.zzca
        public final void zza(int i, zzbo zzboVar) throws IOException {
            zzd(i, 2);
            zza(zzboVar);
        }

        @Override // com.google.android.gms.internal.vision.zzca
        public final void zza(int i, zzdx zzdxVar) throws IOException {
            zzd(1, 3);
            zzf(2, i);
            zzd(3, 2);
            zzb(zzdxVar);
            zzd(1, 4);
        }

        @Override // com.google.android.gms.internal.vision.zzca
        final void zza(int i, zzdx zzdxVar, zzen zzenVar) throws IOException {
            zzd(i, 2);
            zzbf zzbfVar = (zzbf) zzdxVar;
            int zzal = zzbfVar.zzal();
            if (zzal == -1) {
                zzal = zzenVar.zzn(zzbfVar);
                zzbfVar.zzh(zzal);
            }
            zzq(zzal);
            zzenVar.zza(zzdxVar, this.zzhk);
        }

        @Override // com.google.android.gms.internal.vision.zzca
        public final void zza(int i, String str) throws IOException {
            zzd(i, 2);
            zzh(str);
        }

        @Override // com.google.android.gms.internal.vision.zzca
        public final void zza(zzbo zzboVar) throws IOException {
            zzq(zzboVar.size());
            zzboVar.zza(this);
        }

        @Override // com.google.android.gms.internal.vision.zzbn
        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        @Override // com.google.android.gms.internal.vision.zzca
        public final int zzaz() {
            return this.limit - this.position;
        }

        @Override // com.google.android.gms.internal.vision.zzca
        public final void zzb(int i, zzbo zzboVar) throws IOException {
            zzd(1, 3);
            zzf(2, i);
            zza(3, zzboVar);
            zzd(1, 4);
        }

        @Override // com.google.android.gms.internal.vision.zzca
        public final void zzb(int i, boolean z) throws IOException {
            zzd(i, 0);
            zza(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.android.gms.internal.vision.zzca
        public final void zzb(long j) throws IOException {
            if (zzca.zzhj && zzaz() >= 10) {
                while ((j & (-128)) != 0) {
                    byte[] bArr = this.buffer;
                    int i = this.position;
                    this.position = i + 1;
                    zzfl.zza(bArr, i, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                byte[] bArr2 = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                zzfl.zza(bArr2, i2, (byte) j);
                return;
            }
            while ((j & (-128)) != 0) {
                try {
                    byte[] bArr3 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    bArr3[i3] = (byte) ((((int) j) & 127) | 128);
                    j >>>= 7;
                } catch (IndexOutOfBoundsException e) {
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
                }
            }
            byte[] bArr4 = this.buffer;
            int i4 = this.position;
            this.position = i4 + 1;
            bArr4[i4] = (byte) j;
        }

        @Override // com.google.android.gms.internal.vision.zzca
        public final void zzb(zzdx zzdxVar) throws IOException {
            zzq(zzdxVar.zzbl());
            zzdxVar.zzb(this);
        }

        @Override // com.google.android.gms.internal.vision.zzca
        public final void zzc(int i, long j) throws IOException {
            zzd(i, 1);
            zzd(j);
        }

        @Override // com.google.android.gms.internal.vision.zzca
        public final void zzd(int i, int i2) throws IOException {
            zzq((i << 3) | i2);
        }

        @Override // com.google.android.gms.internal.vision.zzca
        public final void zzd(long j) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) j;
                byte[] bArr2 = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr2[i2] = (byte) (j >> 8);
                byte[] bArr3 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr3[i3] = (byte) (j >> 16);
                byte[] bArr4 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr4[i4] = (byte) (j >> 24);
                byte[] bArr5 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr5[i5] = (byte) (j >> 32);
                byte[] bArr6 = this.buffer;
                int i6 = this.position;
                this.position = i6 + 1;
                bArr6[i6] = (byte) (j >> 40);
                byte[] bArr7 = this.buffer;
                int i7 = this.position;
                this.position = i7 + 1;
                bArr7[i7] = (byte) (j >> 48);
                byte[] bArr8 = this.buffer;
                int i8 = this.position;
                this.position = i8 + 1;
                bArr8[i8] = (byte) (j >> 56);
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.vision.zzca
        public final void zzd(byte[] bArr, int i, int i2) throws IOException {
            zzq(i2);
            write(bArr, 0, i2);
        }

        @Override // com.google.android.gms.internal.vision.zzca
        public final void zze(int i, int i2) throws IOException {
            zzd(i, 0);
            zzp(i2);
        }

        @Override // com.google.android.gms.internal.vision.zzca
        public final void zzf(int i, int i2) throws IOException {
            zzd(i, 0);
            zzq(i2);
        }

        @Override // com.google.android.gms.internal.vision.zzca
        public final void zzh(int i, int i2) throws IOException {
            zzd(i, 5);
            zzs(i2);
        }

        @Override // com.google.android.gms.internal.vision.zzca
        public final void zzh(String str) throws IOException {
            int i = this.position;
            try {
                int zzv = zzca.zzv(str.length() * 3);
                int zzv2 = zzca.zzv(str.length());
                if (zzv2 != zzv) {
                    zzq(zzfn.zza(str));
                    this.position = zzfn.zza(str, this.buffer, this.position, zzaz());
                    return;
                }
                this.position = i + zzv2;
                int zza = zzfn.zza(str, this.buffer, this.position, zzaz());
                this.position = i;
                zzq((zza - i) - zzv2);
                this.position = zza;
            } catch (zzfq e) {
                this.position = i;
                zza(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzb(e2);
            }
        }

        @Override // com.google.android.gms.internal.vision.zzca
        public final void zzp(int i) throws IOException {
            if (i >= 0) {
                zzq(i);
            } else {
                zzb(i);
            }
        }

        @Override // com.google.android.gms.internal.vision.zzca
        public final void zzq(int i) throws IOException {
            if (zzca.zzhj && zzaz() >= 10) {
                while ((i & (-128)) != 0) {
                    byte[] bArr = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    zzfl.zza(bArr, i2, (byte) ((i & 127) | 128));
                    i >>>= 7;
                }
                byte[] bArr2 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                zzfl.zza(bArr2, i3, (byte) i);
                return;
            }
            while ((i & (-128)) != 0) {
                try {
                    byte[] bArr3 = this.buffer;
                    int i4 = this.position;
                    this.position = i4 + 1;
                    bArr3[i4] = (byte) ((i & 127) | 128);
                    i >>>= 7;
                } catch (IndexOutOfBoundsException e) {
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
                }
            }
            byte[] bArr4 = this.buffer;
            int i5 = this.position;
            this.position = i5 + 1;
            bArr4[i5] = (byte) i;
        }

        @Override // com.google.android.gms.internal.vision.zzca
        public final void zzs(int i) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) i;
                byte[] bArr2 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr2[i3] = (byte) (i >> 8);
                byte[] bArr3 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr3[i4] = (byte) (i >> 16);
                byte[] bArr4 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr4[i5] = i >> 24;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class zzb extends IOException {
        zzb() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        zzb(java.lang.String r3, java.lang.Throwable r4) {
            /*
                r2 = this;
                java.lang.String r3 = java.lang.String.valueOf(r3)
                int r0 = r3.length()
                java.lang.String r1 = "CodedOutputStream was writing to a flat byte array and ran out of space.: "
                if (r0 == 0) goto L11
                java.lang.String r3 = r1.concat(r3)
                goto L16
            L11:
                java.lang.String r3 = new java.lang.String
                r3.<init>(r1)
            L16:
                r2.<init>(r3, r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzca.zzb.<init>(java.lang.String, java.lang.Throwable):void");
        }

        zzb(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }
    }

    private zzca() {
    }

    public static int zza(int i, zzde zzdeVar) {
        int zzt = zzt(i);
        int zzbl = zzdeVar.zzbl();
        return zzv(zzbl) + zzbl + zzt;
    }

    public static int zza(zzde zzdeVar) {
        int zzbl = zzdeVar.zzbl();
        return zzv(zzbl) + zzbl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(zzdx zzdxVar, zzen zzenVar) {
        zzbf zzbfVar = (zzbf) zzdxVar;
        int zzal = zzbfVar.zzal();
        if (zzal == -1) {
            zzal = zzenVar.zzn(zzbfVar);
            zzbfVar.zzh(zzal);
        }
        return zzv(zzal) + zzal;
    }

    private static int zzaa(int i) {
        return (i >> 31) ^ (i << 1);
    }

    @Deprecated
    public static int zzab(int i) {
        return zzv(i);
    }

    public static int zzb(double d) {
        return 8;
    }

    public static int zzb(int i, double d) {
        return zzt(i) + 8;
    }

    public static int zzb(int i, float f) {
        return zzt(i) + 4;
    }

    public static int zzb(int i, zzde zzdeVar) {
        int zzj = zzj(2, i);
        return zza(3, zzdeVar) + zzj + (zzt(1) << 1);
    }

    public static int zzb(int i, zzdx zzdxVar) {
        return zzc(zzdxVar) + zzt(3) + zzj(2, i) + (zzt(1) << 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(int i, zzdx zzdxVar, zzen zzenVar) {
        return zzt(i) + zza(zzdxVar, zzenVar);
    }

    public static int zzb(int i, String str) {
        return zzi(str) + zzt(i);
    }

    public static int zzb(zzbo zzboVar) {
        int size = zzboVar.size();
        return zzv(size) + size;
    }

    public static int zzb(boolean z) {
        return 1;
    }

    public static int zzc(int i, zzbo zzboVar) {
        int zzt = zzt(i);
        int size = zzboVar.size();
        return zzv(size) + size + zzt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static int zzc(int i, zzdx zzdxVar, zzen zzenVar) {
        int zzt = zzt(i) << 1;
        zzbf zzbfVar = (zzbf) zzdxVar;
        int zzal = zzbfVar.zzal();
        if (zzal == -1) {
            zzal = zzenVar.zzn(zzbfVar);
            zzbfVar.zzh(zzal);
        }
        return zzt + zzal;
    }

    public static int zzc(int i, boolean z) {
        return zzt(i) + 1;
    }

    public static int zzc(zzdx zzdxVar) {
        int zzbl = zzdxVar.zzbl();
        return zzv(zzbl) + zzbl;
    }

    public static int zzd(float f) {
        return 4;
    }

    public static int zzd(int i, long j) {
        return zzf(j) + zzt(i);
    }

    public static int zzd(int i, zzbo zzboVar) {
        int zzj = zzj(2, i);
        return zzc(3, zzboVar) + zzj + (zzt(1) << 1);
    }

    @Deprecated
    public static int zzd(zzdx zzdxVar) {
        return zzdxVar.zzbl();
    }

    public static zzca zzd(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    public static int zze(int i, long j) {
        return zzf(j) + zzt(i);
    }

    public static int zze(long j) {
        return zzf(j);
    }

    public static int zze(byte[] bArr) {
        int length = bArr.length;
        return zzv(length) + length;
    }

    public static int zzf(int i, long j) {
        return zzf(zzj(j)) + zzt(i);
    }

    public static int zzf(long j) {
        int i;
        if (((-128) & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if (((-34359738368L) & j) != 0) {
            i = 6;
            j >>>= 28;
        } else {
            i = 2;
        }
        if (((-2097152) & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & (-16384)) != 0 ? i + 1 : i;
    }

    public static int zzg(int i, long j) {
        return zzt(i) + 8;
    }

    public static int zzg(long j) {
        return zzf(zzj(j));
    }

    public static int zzh(int i, long j) {
        return zzt(i) + 8;
    }

    public static int zzh(long j) {
        return 8;
    }

    public static int zzi(int i, int i2) {
        return zzu(i2) + zzt(i);
    }

    public static int zzi(long j) {
        return 8;
    }

    public static int zzi(String str) {
        int length;
        try {
            length = zzfn.zza(str);
        } catch (zzfq unused) {
            length = str.getBytes(zzct.UTF_8).length;
        }
        return zzv(length) + length;
    }

    public static int zzj(int i, int i2) {
        return zzv(i2) + zzt(i);
    }

    private static long zzj(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static int zzk(int i, int i2) {
        return zzv(zzaa(i2)) + zzt(i);
    }

    public static int zzl(int i, int i2) {
        return zzt(i) + 4;
    }

    public static int zzm(int i, int i2) {
        return zzt(i) + 4;
    }

    public static int zzn(int i, int i2) {
        return zzu(i2) + zzt(i);
    }

    public static int zzt(int i) {
        return zzv(i << 3);
    }

    public static int zzu(int i) {
        if (i >= 0) {
            return zzv(i);
        }
        return 10;
    }

    public static int zzv(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    public static int zzw(int i) {
        return zzv(zzaa(i));
    }

    public static int zzx(int i) {
        return 4;
    }

    public static int zzy(int i) {
        return 4;
    }

    public static int zzz(int i) {
        return zzu(i);
    }

    public abstract void zza(byte b) throws IOException;

    public final void zza(double d) throws IOException {
        zzd(Double.doubleToRawLongBits(d));
    }

    public final void zza(int i, double d) throws IOException {
        zzc(i, Double.doubleToRawLongBits(d));
    }

    public final void zza(int i, float f) throws IOException {
        zzh(i, Float.floatToRawIntBits(f));
    }

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzbo zzboVar) throws IOException;

    public abstract void zza(int i, zzdx zzdxVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(int i, zzdx zzdxVar, zzen zzenVar) throws IOException;

    public abstract void zza(int i, String str) throws IOException;

    public abstract void zza(zzbo zzboVar) throws IOException;

    final void zza(String str, zzfq zzfqVar) throws IOException {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzfqVar);
        byte[] bytes = str.getBytes(zzct.UTF_8);
        try {
            zzq(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (zzb e) {
            throw e;
        } catch (IndexOutOfBoundsException e2) {
            throw new zzb(e2);
        }
    }

    public final void zza(boolean z) throws IOException {
        zza(z ? (byte) 1 : (byte) 0);
    }

    public abstract int zzaz();

    public final void zzb(int i, long j) throws IOException {
        zza(i, zzj(j));
    }

    public abstract void zzb(int i, zzbo zzboVar) throws IOException;

    public abstract void zzb(int i, boolean z) throws IOException;

    public abstract void zzb(long j) throws IOException;

    public abstract void zzb(zzdx zzdxVar) throws IOException;

    public final void zzba() {
        if (zzaz() == 0) {
            return;
        }
        throw new IllegalStateException("Did not write as much data as expected.");
    }

    public final void zzc(float f) throws IOException {
        zzs(Float.floatToRawIntBits(f));
    }

    public abstract void zzc(int i, long j) throws IOException;

    public final void zzc(long j) throws IOException {
        zzb(zzj(j));
    }

    public abstract void zzd(int i, int i2) throws IOException;

    public abstract void zzd(long j) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzd(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zze(int i, int i2) throws IOException;

    public abstract void zzf(int i, int i2) throws IOException;

    public final void zzg(int i, int i2) throws IOException {
        zzf(i, zzaa(i2));
    }

    public abstract void zzh(int i, int i2) throws IOException;

    public abstract void zzh(String str) throws IOException;

    public abstract void zzp(int i) throws IOException;

    public abstract void zzq(int i) throws IOException;

    public final void zzr(int i) throws IOException {
        zzq(zzaa(i));
    }

    public abstract void zzs(int i) throws IOException;
}
