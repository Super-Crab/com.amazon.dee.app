package com.google.android.play.core.assetpacks;

import java.io.IOException;
import java.io.InputStream;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzbj extends InputStream {
    private final InputStream zza;
    private long zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbj(InputStream inputStream, long j) {
        this.zza = inputStream;
        this.zzb = j;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        super.close();
        this.zza.close();
        this.zzb = 0L;
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        long j = this.zzb;
        if (j <= 0) {
            return -1;
        }
        this.zzb = j - 1;
        return this.zza.read();
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        long j = this.zzb;
        if (j <= 0) {
            return -1;
        }
        int read = this.zza.read(bArr, i, (int) Math.min(i2, j));
        if (read != -1) {
            this.zzb -= read;
        }
        return read;
    }
}
