package org.bouncycastle.crypto.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.modes.AEADBlockCipher;
/* loaded from: classes4.dex */
public class CipherOutputStream extends FilterOutputStream {
    private AEADBlockCipher aeadBlockCipher;
    private byte[] buf;
    private BufferedBlockCipher bufferedBlockCipher;
    private final byte[] oneByte;
    private StreamCipher streamCipher;

    public CipherOutputStream(OutputStream outputStream, BufferedBlockCipher bufferedBlockCipher) {
        super(outputStream);
        this.oneByte = new byte[1];
        this.bufferedBlockCipher = bufferedBlockCipher;
    }

    public CipherOutputStream(OutputStream outputStream, StreamCipher streamCipher) {
        super(outputStream);
        this.oneByte = new byte[1];
        this.streamCipher = streamCipher;
    }

    public CipherOutputStream(OutputStream outputStream, AEADBlockCipher aEADBlockCipher) {
        super(outputStream);
        this.oneByte = new byte[1];
        this.aeadBlockCipher = aEADBlockCipher;
    }

    private void ensureCapacity(int i, boolean z) {
        if (z) {
            BufferedBlockCipher bufferedBlockCipher = this.bufferedBlockCipher;
            if (bufferedBlockCipher != null) {
                i = bufferedBlockCipher.getOutputSize(i);
            } else {
                AEADBlockCipher aEADBlockCipher = this.aeadBlockCipher;
                if (aEADBlockCipher != null) {
                    i = aEADBlockCipher.getOutputSize(i);
                }
            }
        } else {
            BufferedBlockCipher bufferedBlockCipher2 = this.bufferedBlockCipher;
            if (bufferedBlockCipher2 != null) {
                i = bufferedBlockCipher2.getUpdateOutputSize(i);
            } else {
                AEADBlockCipher aEADBlockCipher2 = this.aeadBlockCipher;
                if (aEADBlockCipher2 != null) {
                    i = aEADBlockCipher2.getUpdateOutputSize(i);
                }
            }
        }
        byte[] bArr = this.buf;
        if (bArr == null || bArr.length < i) {
            this.buf = new byte[i];
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0058, code lost:
        if (r1 != null) goto L11;
     */
    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void close() throws java.io.IOException {
        /*
            r4 = this;
            r0 = 0
            r1 = 1
            r4.ensureCapacity(r0, r1)
            org.bouncycastle.crypto.BufferedBlockCipher r1 = r4.bufferedBlockCipher     // Catch: java.lang.Exception -> L3d org.bouncycastle.crypto.InvalidCipherTextException -> L46
            if (r1 == 0) goto L1b
            org.bouncycastle.crypto.BufferedBlockCipher r1 = r4.bufferedBlockCipher     // Catch: java.lang.Exception -> L3d org.bouncycastle.crypto.InvalidCipherTextException -> L46
            byte[] r2 = r4.buf     // Catch: java.lang.Exception -> L3d org.bouncycastle.crypto.InvalidCipherTextException -> L46
            int r1 = r1.doFinal(r2, r0)     // Catch: java.lang.Exception -> L3d org.bouncycastle.crypto.InvalidCipherTextException -> L46
            if (r1 == 0) goto L3a
            java.io.OutputStream r2 = r4.out     // Catch: java.lang.Exception -> L3d org.bouncycastle.crypto.InvalidCipherTextException -> L46
            byte[] r3 = r4.buf     // Catch: java.lang.Exception -> L3d org.bouncycastle.crypto.InvalidCipherTextException -> L46
            r2.write(r3, r0, r1)     // Catch: java.lang.Exception -> L3d org.bouncycastle.crypto.InvalidCipherTextException -> L46
            goto L3a
        L1b:
            org.bouncycastle.crypto.modes.AEADBlockCipher r1 = r4.aeadBlockCipher     // Catch: java.lang.Exception -> L3d org.bouncycastle.crypto.InvalidCipherTextException -> L46
            if (r1 == 0) goto L31
            org.bouncycastle.crypto.modes.AEADBlockCipher r1 = r4.aeadBlockCipher     // Catch: java.lang.Exception -> L3d org.bouncycastle.crypto.InvalidCipherTextException -> L46
            byte[] r2 = r4.buf     // Catch: java.lang.Exception -> L3d org.bouncycastle.crypto.InvalidCipherTextException -> L46
            int r1 = r1.doFinal(r2, r0)     // Catch: java.lang.Exception -> L3d org.bouncycastle.crypto.InvalidCipherTextException -> L46
            if (r1 == 0) goto L3a
            java.io.OutputStream r2 = r4.out     // Catch: java.lang.Exception -> L3d org.bouncycastle.crypto.InvalidCipherTextException -> L46
            byte[] r3 = r4.buf     // Catch: java.lang.Exception -> L3d org.bouncycastle.crypto.InvalidCipherTextException -> L46
            r2.write(r3, r0, r1)     // Catch: java.lang.Exception -> L3d org.bouncycastle.crypto.InvalidCipherTextException -> L46
            goto L3a
        L31:
            org.bouncycastle.crypto.StreamCipher r0 = r4.streamCipher     // Catch: java.lang.Exception -> L3d org.bouncycastle.crypto.InvalidCipherTextException -> L46
            if (r0 == 0) goto L3a
            org.bouncycastle.crypto.StreamCipher r0 = r4.streamCipher     // Catch: java.lang.Exception -> L3d org.bouncycastle.crypto.InvalidCipherTextException -> L46
            r0.reset()     // Catch: java.lang.Exception -> L3d org.bouncycastle.crypto.InvalidCipherTextException -> L46
        L3a:
            r0 = 0
            r1 = r0
            goto L4e
        L3d:
            r0 = move-exception
            org.bouncycastle.crypto.io.CipherIOException r1 = new org.bouncycastle.crypto.io.CipherIOException
            java.lang.String r2 = "Error closing stream: "
            r1.<init>(r2, r0)
            goto L4e
        L46:
            r0 = move-exception
            org.bouncycastle.crypto.io.InvalidCipherTextIOException r1 = new org.bouncycastle.crypto.io.InvalidCipherTextIOException
            java.lang.String r2 = "Error finalising cipher data"
            r1.<init>(r2, r0)
        L4e:
            r4.flush()     // Catch: java.io.IOException -> L57
            java.io.OutputStream r0 = r4.out     // Catch: java.io.IOException -> L57
            r0.close()     // Catch: java.io.IOException -> L57
            goto L5b
        L57:
            r0 = move-exception
            if (r1 != 0) goto L5b
            goto L5c
        L5b:
            r0 = r1
        L5c:
            if (r0 != 0) goto L5f
            return
        L5f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.io.CipherOutputStream.close():void");
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        ((FilterOutputStream) this).out.flush();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.oneByte;
        byte b = (byte) i;
        bArr[0] = b;
        StreamCipher streamCipher = this.streamCipher;
        if (streamCipher != null) {
            ((FilterOutputStream) this).out.write(streamCipher.returnByte(b));
        } else {
            write(bArr, 0, 1);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        ensureCapacity(i2, false);
        BufferedBlockCipher bufferedBlockCipher = this.bufferedBlockCipher;
        if (bufferedBlockCipher != null) {
            int processBytes = bufferedBlockCipher.processBytes(bArr, i, i2, this.buf, 0);
            if (processBytes == 0) {
                return;
            }
            ((FilterOutputStream) this).out.write(this.buf, 0, processBytes);
            return;
        }
        AEADBlockCipher aEADBlockCipher = this.aeadBlockCipher;
        if (aEADBlockCipher == null) {
            this.streamCipher.processBytes(bArr, i, i2, this.buf, 0);
            ((FilterOutputStream) this).out.write(this.buf, 0, i2);
            return;
        }
        int processBytes2 = aEADBlockCipher.processBytes(bArr, i, i2, this.buf, 0);
        if (processBytes2 == 0) {
            return;
        }
        ((FilterOutputStream) this).out.write(this.buf, 0, processBytes2);
    }
}
