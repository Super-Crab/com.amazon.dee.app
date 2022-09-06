package org.bouncycastle.jcajce.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.crypto.Cipher;
/* loaded from: classes4.dex */
public class CipherOutputStream extends FilterOutputStream {
    private final Cipher cipher;
    private final byte[] oneByte;

    public CipherOutputStream(OutputStream outputStream, Cipher cipher) {
        super(outputStream);
        this.oneByte = new byte[1];
        this.cipher = cipher;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002f, code lost:
        if (r1 != null) goto L9;
     */
    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void close() throws java.io.IOException {
        /*
            r3 = this;
            javax.crypto.Cipher r0 = r3.cipher     // Catch: java.lang.Exception -> L10 java.security.GeneralSecurityException -> L1d
            byte[] r0 = r0.doFinal()     // Catch: java.lang.Exception -> L10 java.security.GeneralSecurityException -> L1d
            if (r0 == 0) goto Ld
            java.io.OutputStream r1 = r3.out     // Catch: java.lang.Exception -> L10 java.security.GeneralSecurityException -> L1d
            r1.write(r0)     // Catch: java.lang.Exception -> L10 java.security.GeneralSecurityException -> L1d
        Ld:
            r0 = 0
            r1 = r0
            goto L25
        L10:
            r0 = move-exception
            java.io.IOException r1 = new java.io.IOException
            java.lang.String r2 = "Error closing stream: "
            java.lang.String r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline68(r2, r0)
            r1.<init>(r0)
            goto L25
        L1d:
            r0 = move-exception
            org.bouncycastle.crypto.io.InvalidCipherTextIOException r1 = new org.bouncycastle.crypto.io.InvalidCipherTextIOException
            java.lang.String r2 = "Error during cipher finalisation"
            r1.<init>(r2, r0)
        L25:
            r3.flush()     // Catch: java.io.IOException -> L2e
            java.io.OutputStream r0 = r3.out     // Catch: java.io.IOException -> L2e
            r0.close()     // Catch: java.io.IOException -> L2e
            goto L32
        L2e:
            r0 = move-exception
            if (r1 != 0) goto L32
            goto L33
        L32:
            r0 = r1
        L33:
            if (r0 != 0) goto L36
            return
        L36:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.io.CipherOutputStream.close():void");
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        ((FilterOutputStream) this).out.flush();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.oneByte;
        bArr[0] = (byte) i;
        write(bArr, 0, 1);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        byte[] update = this.cipher.update(bArr, i, i2);
        if (update != null) {
            ((FilterOutputStream) this).out.write(update);
        }
    }
}
